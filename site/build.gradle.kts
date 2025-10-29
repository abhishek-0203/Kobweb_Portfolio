import com.varabyte.kobweb.gradle.application.util.configAsKobwebApplication
import kotlinx.html.HEAD
import kotlinx.html.meta

plugins {
    alias(libs.plugins.kotlin.multiplatform)
    alias(libs.plugins.compose.compiler)
    alias(libs.plugins.kobweb.application)
    alias(libs.plugins.kobwebx.markdown)
}

fun HEAD.meta(property: String, content: String) {
    meta {
        attributes["property"] = property
        this.content = content
    }
}

group = "com.mano.ashwa"
version = "1.0-SNAPSHOT"

kobweb {
    app {
        index {
            val url = ""
            val authorStr = "Abhishek Verma"

            val descriptionStr = """
			Hi, I’m Abhishek Verma, an aspiring software engineer from India, passionate about learning, coding, and 
            creating impactful applications through technology.
			Discover my projects and my blog on this website.
		""".trimIndent()

            val image = "$url/images/avatar.webp"

            description.set(descriptionStr)

            globals.put("author", authorStr)
            globals.put("description", descriptionStr)
            globals.put("image", image) // Add image to globals so it's available in templates and to avoid an unused-property warning
            globals.put("url", url)

            faviconPath = "/logo.png"
        }
    }
}

kotlin {
    // This example is frontend only. However, for a fullstack app, you can uncomment the includeServer parameter
    // and the `jvmMain` source set below.
    configAsKobwebApplication("ashwa" /*, includeServer = true*/)

    sourceSets {
        jsMain.dependencies {
            implementation(libs.compose.runtime)
            implementation(libs.compose.html.core)
            implementation(libs.kobweb.core)
            implementation(libs.kobweb.silk)
            // This default template uses built-in SVG icons, but what's available is limited.
            // Uncomment the following if you want access to a large set of font-awesome icons:
            implementation(libs.silk.icons.fa)
            implementation(libs.kobwebx.markdown)
            implementation(libs.kotlin.bootstrap)

            // Provide bootstrap's peer dependency to avoid the unmet peer warning during npm install
            // @popperjs/core is required by bootstrap@5; pin to a compatible version.
            implementation(npm("@popperjs/core", "^2.11.8"))
        }
    }
}
