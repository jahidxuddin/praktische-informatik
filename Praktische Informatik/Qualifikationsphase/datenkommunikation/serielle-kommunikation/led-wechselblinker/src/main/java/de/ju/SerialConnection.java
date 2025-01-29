package de.ju;

import serialio.Serial;

public interface SerialConnection {
    void onSuccess(Serial serial);
    void onFailure(String error);
}
