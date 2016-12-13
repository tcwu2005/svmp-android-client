/*
 Copyright (c) 2013 The MITRE Corporation, All Rights Reserved.

 Licensed under the Apache License, Version 2.0 (the "License");
 you may not use this work except in compliance with the License.
 You may obtain a copy of the License at

     http://www.apache.org/licenses/LICENSE-2.0

 Unless required by applicable law or agreed to in writing, software
 distributed under the License is distributed on an "AS IS" BASIS,
 WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 See the License for the specific language governing permissions and
 limitations under the License.
 */
package org.mitre.svmp.common;

import android.os.Build;
import org.itri.vmi.client.R;

/**
 * @author Joe Portner
 */
public interface Constants {
    // to use a trust store to pin certain CA certificates, replace "res/raw/client_truststore.bks" with your trust store
    // this password is used to open that file
    public static final String TRUSTSTORE_PASSWORD = "changeme_clienttstorepass";
    //************************************************POC
    public String CONN_DESC []  = new String[]{"VMI00","VMI01","VMI02","VMI03","VMI04","VMI05","VMI06","VMI07","VMI08","VMI09","VMI10","VMI11","VMI12","VMI13"
    											,"VMI14","VMI15","VMI16","VMI17"};
    public String CONN_HOST = "220.228.197.210";
    public String USER [] =new String[]{ "user0","user1","user2","user3","user4","user5","user6","user7","user8","user9","user10","user11","user12","user13"
    									,"user14","user15","user16","user17"};
    public String USR_PWD = "Useruser111@";
    //********************************************************

    
    public static final int DEFAULT_PORT = 3000;
    public static final String ACTION_REFRESH = "org.mitre.svmp.ACTION_REFRESH"; // causes SvmpActivity to refresh its layout
    public static final String ACTION_STOP_SERVICE = "org.mitre.svmp.ACTION_STOP_SERVICE"; // causes
    public static final String ACTION_LAUNCH_APP = "org.mitre.svmp.LAUNCH_APP";
    //public static final String PERMISSION_REFRESH = "org.mitre.svmp.PERMISSION_REFRESH"; // required to send ACTION_REFRESH intent
    public static final String PERMISSION_REFRESH = "org.mitre.svmp.REFRESH";
    public static final boolean API_19 = (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT);
    public static final boolean API_15 = (Build.VERSION.SDK_INT >= Build.VERSION_CODES.ICE_CREAM_SANDWICH_MR1);
    public static final boolean API_14 = (Build.VERSION.SDK_INT >= Build.VERSION_CODES.ICE_CREAM_SANDWICH);

    // used to determine what the EncryptionType for each connection is
    public static final int ENCRYPTION_NONE = 0;
    public static final int ENCRYPTION_SSLTLS = 1;

    // Enabled ciphers (in order from most to least preferred)
    // Taken from: http://op-co.de/blog/posts/android_ssl_downgrade/
    public static final String[] ENABLED_CIPHERS = {
            //"TLS_ECDHE_RSA_WITH_AES_256_CBC_SHA", // note: not supported by node 0.10.x or Android?
            //"TLS_ECDHE_RSA_WITH_AES_128_CBC_SHA", // note: not supported by node 0.10.x or Android?
            "TLS_DHE_RSA_WITH_AES_256_CBC_SHA",   // note: not supported by node 0.10.x, should use at least 2048-bit DH
            "TLS_DHE_RSA_WITH_AES_128_CBC_SHA",   // note: not supported by node 0.10.x, should use at least 2048-bit DH
            "TLS_RSA_WITH_AES_256_CBC_SHA",
            "TLS_RSA_WITH_AES_128_CBC_SHA"
    };

    // Enabled protocols (in order from most to least preferred)
    public static final String[] ENABLED_PROTOCOLS = {
            "TLSv1.2" // TLS v1.1 and TLS v1.0 disabled
    };

    // used to map sensor IDs to key names
    public static final int[] PREFERENCES_SENSORS_KEYS = {
            R.string.preferenceKey_sensor_accelerometer,
            R.string.preferenceKey_sensor_magneticField,
            R.string.preferenceKey_sensor_orientation,         // virtual
            R.string.preferenceKey_sensor_gyroscope,
            R.string.preferenceKey_sensor_light,
            R.string.preferenceKey_sensor_pressure,
            R.string.preferenceKey_sensor_temperature,
            R.string.preferenceKey_sensor_proximity,
            R.string.preferenceKey_sensor_gravity,             // virtual
            R.string.preferenceKey_sensor_linearAcceleration,  // virtual
            R.string.preferenceKey_sensor_rotationVector,      // virtual
            R.string.preferenceKey_sensor_relativeHumidity,
            R.string.preferenceKey_sensor_ambientTemperature
    };

    // used to map sensor IDs to default values
    public static final int[] PREFERENCES_SENSORS_DEFAULTVALUES = {
            R.string.preferenceValue_sensor_accelerometer,
            R.string.preferenceValue_sensor_magneticField,
            R.string.preferenceValue_sensor_orientation,         // virtual
            R.string.preferenceValue_sensor_gyroscope,
            R.string.preferenceValue_sensor_light,
            R.string.preferenceValue_sensor_pressure,
            R.string.preferenceValue_sensor_temperature,
            R.string.preferenceValue_sensor_proximity,
            R.string.preferenceValue_sensor_gravity,             // virtual
            R.string.preferenceValue_sensor_linearAcceleration,  // virtual
            R.string.preferenceValue_sensor_rotationVector,      // virtual
            R.string.preferenceValue_sensor_relativeHumidity,
            R.string.preferenceValue_sensor_ambientTemperature
    };

    // multiplier for minimum sensor updates
    public static final double[] SENSOR_MINIMUM_UPDATE_SCALES = {
            1.0, // accelerometer
            1.0, // magnetic field
            1.0, // orientation
            1.0, // gyroscope
            1.0, // light
            1.0, // pressure
            1.0, // temperature
            1.0, // proximity
            1.0, // gravity
            1.0, // linear acceleration
            1.0, // rotation vector
            1.0, // relative humidity
            1.0  // ambient temperature
    };
}
