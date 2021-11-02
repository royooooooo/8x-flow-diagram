package doxflow.models.diagram

import common.Element
import common.Diagram.Color.YELLOW
import doxflow.dsl.context
import doxflow.models.diagram.Relationship.Companion.NONE
import doxflow.models.diagram.Relationship.Companion.PLAY_TO

class Role(override val element: Element, type: Type, val context: context) : Party {
    init {
        element.backgroundColor = YELLOW
        element.stereoType = type.name.lowercase()
    }

    enum class Type {
        PARTY,
        DOMAIN,
        THIRD_SYSTEM,
        EVIDENCE
    }

    infix fun played(participant: Participant): Role = apply {
        participant.element.relate(element, PLAY_TO)
    }

    infix fun relate(genericEvidence: Evidence<*>) {
        element.relate(genericEvidence.element, NONE)
    }

    override fun toString(): String = buildString {
        appendLine(element)
        appendLine(element.generateRelationships())
    }
}
