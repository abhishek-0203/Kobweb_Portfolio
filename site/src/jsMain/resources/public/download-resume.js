async function forceDownload(url, filename) {
  try {
    // Normalize absolute URL for leading-root paths so fetch works regardless of base href
    const absoluteUrl = (url && url.startsWith('/')) ? (window.location.origin + url) : url;

    const resp = await fetch(absoluteUrl, { mode: 'same-origin' });
    if (!resp.ok) throw new Error('Network response was not ok: ' + resp.status);

    const arrayBuffer = await resp.arrayBuffer();
    const contentType = resp.headers.get('content-type') || 'application/octet-stream';
    const blob = new Blob([arrayBuffer], { type: contentType });

    const link = document.createElement('a');
    link.href = URL.createObjectURL(blob);
    link.download = filename || (absoluteUrl ? absoluteUrl.split('/').pop() : 'download');
    // Some browsers require the anchor to be in the document
    document.body.appendChild(link);
    link.click();
    // Remove the element but don't revoke immediately; allow time for the browser to start the download
    link.remove();
    // Revoke after a delay to avoid aborting the download in some environments
    setTimeout(() => URL.revokeObjectURL(link.href), 60 * 1000);
  } catch (e) {
    // fallback: open in new tab — user can save from there
    console.warn('forceDownload failed, opening URL instead:', e);
    window.open(url, '_blank');
  }
}

window.forceDownload = forceDownload;
