package com.amazon.ata.hashingset.partsmanager;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class PartManager {
    private Set<DevicePart> deviceParts = new HashSet<>();

    // Use the Hash Code to determine the index of an element in an array
    //
    // Since Hash Code collisions may occur, we will store the elements in an Arraylist in the array
    // rather than storing the individual elements in the array
    //
    // Determine the maximum size - (10 elements) - need the size of an array when it's initialized.
    // To add an element to the array.

    private final int numParts = 10;  // The number of elements in the array as a constant

    // Define an array of ArrayList<DevicePart> - each element will be an ArrayList of DevicePart objects
    //              datatype[] name  = new datatype[#-elements]
    private List<DevicePart>[] parts = new ArrayList[numParts]; // an array of 10 ArrayLists

    public void addDevicePart(DevicePart devicePart) {
        boolean isAdded = deviceParts.add(devicePart);

        // To add an element to the array:
        //      1. Find the Hash Code for an object
        //      2. Calculate the index for the array using the Hash Code
        // Use Math.abs() to be sure the index does not return negative - absolute value
        int partIndex = Math.abs(devicePart.hashCode() % numParts); // index based on the Hash Code for object to store

        //      3. Store the object in the array for the index determined
        if (parts[partIndex] != null) { // Do we already have an object at this index? (Hash Code collision has occurred)
                                        //      yes - add the object the ArrayList
            parts[partIndex].add(devicePart);
        } else {
                                        //      no - instantiate an ArrayList in the element & add the object
            parts[partIndex] = new ArrayList<>();
            parts[partIndex].add(devicePart);
        }
        return; // not required since method is void - it's added, so we can stop the debugger here
    }

    /**
     * Find & return an object in our array of objects or null if it's not found
     */
    public DevicePart findDevicePart(DevicePart requestedDevicePart) {
        // Instantiate an object to be returned
        DevicePart returnedObject = null;

        // Use the Hash Code of the requested object to determine its index in the array
        int partIndex = Math.abs(requestedDevicePart.hashCode() % numParts);

        // Check to see if the element in the array for the index has an ArrayList, if not there are no objects
        if (parts[partIndex] != null) {
            // If there is an ArrayList in the element, search it for the requestedDevicePart
            if (parts[partIndex].contains(requestedDevicePart)) {
                // If found, set the returned object to it
                //      .get() of ArrayList to retrieve the object, .get() needs the index of the object we want
                //       we use indexOf() to find the index of the request object
                returnedObject = parts[partIndex].get(parts[partIndex].indexOf(requestedDevicePart));
            }
        }
        // If not found, return object that was initialized to null
        // Return the found object
        return returnedObject;
    }

    public void printDeviceParts() {
        for (DevicePart devicePart: deviceParts) {
            System.out.println(devicePart);
        }
    }
}
