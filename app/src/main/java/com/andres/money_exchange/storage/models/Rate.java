package com.andres.money_exchange.storage.models;

import io.realm.RealmObject;

/**
 * Created by andresdavid on 21/09/16.
 */
public class Rate extends RealmObject {

        private String key;
        private double value;

        public String getKey() {
                return key;
        }

        public void setKey(String key) {
                this.key = key;
        }

        public double getValue() {
                return value;
        }

        public void setValue(double value) {
                this.value = value;
        }
}
