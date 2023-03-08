package com.example.sensorreader;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Context;
import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.TextView;

import java.util.List;

public class MainActivity extends AppCompatActivity implements SensorEventListener {

    private SensorManager mSensorManager;

    private Sensor mSensorProximity;
    private Sensor mSensorLight;
    private Sensor mSensorAmbient;
    private Sensor mSensorPressure;
    private Sensor mSensorMagnetic;
    private Sensor mSensorHumidity;

    //mencoba sensor yang ada di device
    private Sensor mSensorAccelerometor;
    private Sensor mSensorGyroscope;

    private TextView mTextSensorLight;
    private TextView mTextSensorProximity;
    private TextView mTextSensorAmbient;
    private TextView mTextSensorPressure;
    private TextView mTextSensorMagnetic;
    private TextView mTextSensorHumidity;
    private TextView mTextSensorAccelerometor;
    private TextView mTextSensorGyroscope;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);

        //menampung list sensor
        List<Sensor> sensorList = mSensorManager.getSensorList(Sensor.TYPE_ALL);
        StringBuilder sensorText = new StringBuilder();

        //menampilkan list sensor
        for (Sensor currentSensor : sensorList){
            sensorText.append(currentSensor.getName()).
                    append(System.getProperty("line.separator"));
        }

        TextView sensorTextView = findViewById(R.id.sensor_list);
        sensorTextView.setText(sensorText);

        //inisiasi id TextView
        mTextSensorLight = findViewById(R.id.label_light);
        mTextSensorProximity = findViewById(R.id.label_proximity);
        mTextSensorAmbient = findViewById(R.id.label_ambient);
        mTextSensorPressure = findViewById(R.id.label_pressure);
        mTextSensorMagnetic = findViewById(R.id.label_magnetic);
        mTextSensorHumidity = findViewById(R.id.label_humidity);
        mTextSensorAccelerometor = findViewById(R.id.label_accelerometer);
        mTextSensorGyroscope = findViewById(R.id.label_gyroscope);

        //mengambil sensor dari sensor manager
        mSensorProximity = mSensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY);
        mSensorLight = mSensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);
        mSensorAmbient = mSensorManager.getDefaultSensor(Sensor.TYPE_AMBIENT_TEMPERATURE);
        mSensorPressure = mSensorManager.getDefaultSensor(Sensor.TYPE_PRESSURE);
        mSensorMagnetic = mSensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);
        mSensorHumidity = mSensorManager.getDefaultSensor(Sensor.TYPE_RELATIVE_HUMIDITY);
        mSensorAccelerometor = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        mSensorGyroscope = mSensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE);

        //mengecek sensor
        String sensor_error = "No sensor";
        if (mSensorLight == null){
            mTextSensorLight.setText(sensor_error);
        }
        if (mSensorProximity == null){
            mTextSensorProximity.setText(sensor_error);
        }
        if (mSensorAmbient == null){
            mTextSensorAmbient.setText(sensor_error);
        }
        if (mSensorPressure == null){
            mTextSensorPressure.setText(sensor_error);
        }
        if (mSensorMagnetic == null){
            mTextSensorMagnetic.setText(sensor_error);
        }
        if (mSensorHumidity == null){
            mTextSensorHumidity.setText(sensor_error);
        }
        if (mSensorAccelerometor == null){
            mTextSensorAccelerometor.setText(sensor_error);
        }
        if (mSensorGyroscope == null){
            mTextSensorGyroscope.setText(sensor_error);
        }
    }

    //sebelum mengetahui perubahan data, harus di register terlebih dahulu
    @Override
    protected void onStart() {
        super.onStart();
        if (mSensorProximity != null) {
            mSensorManager.registerListener(this,mSensorProximity,
                    SensorManager.SENSOR_DELAY_NORMAL);
        }
        if (mSensorLight != null) {
            mSensorManager.registerListener(this, mSensorLight,
                    SensorManager.SENSOR_DELAY_NORMAL);
        }
        if (mSensorAmbient != null) {
            mSensorManager.registerListener(this, mSensorAmbient,
                    SensorManager.SENSOR_DELAY_NORMAL);
        }
        if (mSensorPressure != null) {
            mSensorManager.registerListener(this, mSensorPressure,
                    SensorManager.SENSOR_DELAY_NORMAL);
        }
        if (mSensorMagnetic != null) {
            mSensorManager.registerListener(this, mSensorMagnetic,
                    SensorManager.SENSOR_DELAY_NORMAL);
        }
        if (mSensorHumidity != null) {
            mSensorManager.registerListener(this, mSensorHumidity,
                    SensorManager.SENSOR_DELAY_NORMAL);
        }
        if (mSensorAccelerometor != null) {
            mSensorManager.registerListener(this, mSensorAccelerometor,
                    SensorManager.SENSOR_DELAY_NORMAL);
        }
        if (mSensorGyroscope != null) {
            mSensorManager.registerListener(this, mSensorGyroscope,
                    SensorManager.SENSOR_DELAY_NORMAL);
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        mSensorManager.unregisterListener(this);
    }

    // untuk mengetahui perubahan data dari sensor
    // %1$.2f mengambil string format parameter yang diinputkan
    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        int sensorType = sensorEvent.sensor.getType();
        float currentValue = sensorEvent.values[0];
        switch (sensorType){
            case Sensor.TYPE_LIGHT:
                mTextSensorLight.setText(
                        String.format("Light sensor : %1$.2f", currentValue));
                changeBackgroundColor(currentValue);
                break;
            case Sensor.TYPE_PROXIMITY:
                mTextSensorProximity.setText(
                        String.format("Proximity sensor : %1$.2f", currentValue));
                break;
            case Sensor.TYPE_AMBIENT_TEMPERATURE:
                mTextSensorAmbient.setText(
                        String.format("Ambient temperature sensor : %1$.2f", currentValue));
            case Sensor.TYPE_PRESSURE:
                mTextSensorPressure.setText(
                        String.format("Pressure sensor : %1$.2f", currentValue));
            case Sensor.TYPE_MAGNETIC_FIELD:
                mTextSensorMagnetic.setText(
                        String.format("Magnetic field sensor : %1$.2f", currentValue));
            case Sensor.TYPE_RELATIVE_HUMIDITY:
                mTextSensorHumidity.setText(
                        String.format("Relative humidity sensor : %1$.2f", currentValue));
            case Sensor.TYPE_ACCELEROMETER:
                mTextSensorAccelerometor.setText(
                        String.format("Accelerometer sensor : %1$.2f", currentValue));
            case Sensor.TYPE_GYROSCOPE:
                mTextSensorGyroscope.setText(
                        String.format("Gyroscope sensor : %1$.2f", currentValue));
            default:
        }
    }

    //mengubah warna background
    private void changeBackgroundColor(float currentValue){
        ConstraintLayout layout = findViewById(R.id.layout_constraint);
        if (currentValue >= 2000 && currentValue <= 4000){
            layout.setBackgroundColor(Color.RED);
        } else if (currentValue >= 0 && currentValue < 2000){
            layout.setBackgroundColor(Color.BLUE);
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }
}