package com.mano.ashwa.model

import com.varabyte.kobweb.compose.ui.graphics.Color
import com.varabyte.kobweb.compose.ui.graphics.Colors

/**
 * Represents a skill category with associated competencies.
 *
 * @property title The name of the skill category (e.g., "Kotlin", "Backend").
 * @property skills List of specific skills or competencies within this category.
 * @property icon Emoji or icon representing this skill category.
 * @property color Accent color for visual distinction.
 */
data class SkillData(
    val title: String,
    val skills: List<String>,
    val icon: String = "",
    val color: Color = Colors.Transparent
)

/**
 * Represents professional work experience.
 *
 * @property companyName Name of the employer/organization.
 * @property title Job title or position description.
 * @property duration Time period of employment (e.g., "Jan 2020 - Present").
 * @property role Specific role or responsibility.
 * @property subTitle Additional context or subtitle.
 * @property skills List of skills utilized or developed in this role.
 * @property icon Emoji or icon representing the company/role.
 * @property color Accent color for visual distinction.
 */
data class ExperienceData(
    val companyName: String,
    val title: String,
    val duration: String,
    val role: String,
    val subTitle: String = "",
    val skills: List<String> = emptyList(),
    val icon: String = "",
    val color: Color = Colors.Transparent
)

/**
 * Represents a portfolio project.
 *
 * @property name Project name or title.
 * @property description Brief description of the project and its purpose.
 * @property duration Development timeline or status (e.g., "2023", "Present").
 * @property role Your role in the project (e.g., "Full Stack Developer").
 * @property technologies List of technologies, frameworks, or tools used.
 * @property icon Emoji or icon representing the project type.
 * @property color Accent color for visual distinction.
 */
data class ProjectData(
    val name: String,
    val description: String,
    val duration: String,
    val role: String,
    val technologies: List<String> = emptyList(),
    val icon: String = "",
    val color: Color = Colors.Transparent
)
