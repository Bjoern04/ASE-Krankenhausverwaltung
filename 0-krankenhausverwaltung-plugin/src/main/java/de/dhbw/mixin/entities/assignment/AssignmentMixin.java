package de.dhbw.mixin.entities.assignment;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import de.dhbw.aggregates.assignment.entity.Assignment;

@JsonDeserialize(builder = Assignment.AssignmentBuilder.class)
public class AssignmentMixin {
}
