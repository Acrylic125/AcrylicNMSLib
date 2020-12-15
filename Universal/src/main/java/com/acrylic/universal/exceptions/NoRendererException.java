package com.acrylic.universal.exceptions;

public class NoRendererException extends RuntimeException {

    @Override
    public String toString() {
        return "NoRendererException: A Packet Renderer is required to use this.";
    }
}
