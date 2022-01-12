package com.kioea.www.adt.listtype

import spock.lang.*

import java.lang.invoke.MethodHandleImpl

class AListTest extends Specification {
    //Field entry of type T[] - was not mocked since Mockito doesn't mock arrays
    AList aList = new AList(2)

    def "test add"() {
        when:
        boolean result = aList.add("a")

        then:
        result
    }

    def "test add 2"() {
        when:
        boolean result = aList.add(1, "b")

        then:
        result
    }

    def "test remove"() {
        when:
        String result = aList.remove(0)

        then:
        result == "a"
    }

    def "test clear"() {
        when:
        aList.clear()

        then:
        true//todo - validate something
    }

    def "test replace"() {
        when:
        boolean result = aList.replace("b", "c")

        then:
        result
    }

    def "test get Entry"() {
        when:
        String result = aList.getEntry(0)

        then:
        result == "a"
    }

    def "test contains"() {
        when:
        boolean result = aList.contains("b")

        then:
        result
    }

    def "test is Empty"() {
        when:
        boolean result = aList.isEmpty()

        then:
        result
    }

    def "test is Full"() {
        when:
        boolean result = aList.isFull()

        then:
        result
    }

    def "test display"() {
        when:
        aList.display()

        then:
        false//todo - validate something
    }
}

//Generated with love by TestMe :) Please report issues and submit feature requests at: http://weirddev.com/forum#!/testme