package de.ju;

import serialio.Serial;

import java.io.IOException;

public class SerialWrapper {
    public static void createSerialConnection(SerialDetails details, SerialConnection handler) {
        Serial serial;
        try {
            serial = new Serial(details.portName(), details.baut(), details.dataBits(), details.stopBits(), details.parity());
        } catch (IOException e) {
            String failureMessage = String.format(
                    "Failed to initialize Serial connection with port '%s' and parameters (baud rate: %d, data bits: %d, stop bits: %d, parity: %s). Error: %s",
                    details.portName(), details.baut(), details.dataBits(), details.stopBits(), details.parity(), e.getMessage()
            );
            handler.onFailure(failureMessage);
            return;
        }

        try {
            if (!serial.open()) {
                String failureMessage = String.format(
                        "Failed to open Serial connection with port '%s' and parameters (baud rate: %d, data bits: %d, stop bits: %d, parity: %s). The connection could not be established.",
                        details.portName(), details.baut(), details.dataBits(), details.stopBits(), details.parity()
                );
                handler.onFailure(failureMessage);
                return;
            }
        } catch (IOException e) {
            String failureMessage = String.format(
                    "Exception occurred while trying to open Serial connection with port '%s' and parameters (baud rate: %d, data bits: %d, stop bits: %d, parity: %s). Error: %s",
                    details.portName(), details.baut(), details.dataBits(), details.stopBits(), details.parity(), e.getMessage()
            );
            handler.onFailure(failureMessage);
            return;
        }

        handler.onSuccess(serial);
    }
}
