package com.mtt.test.page.components;

/**
 */
public interface WebFormComponent {

    /**
     * Detaches this component from the underlying browser automation environment. The idea being that
     * it permanently persists it's current state and does not support any further modification.
     */
    void detach();
}
