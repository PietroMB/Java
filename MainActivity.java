package race.mqtttest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button b = this.findViewById(R.id.button);

        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                MQTTSender("topic/test","content","tcp://192.168.1.2:1883","Test","username","password");

            }
        });
    }

    private void MQTTSender(String topic, String content, String broker, String clientId, String username, String password){

        int qos = 2;

        MemoryPersistence persistence = new MemoryPersistence();

        try {
            MqttClient sampleClient = new MqttClient(broker, clientId, persistence);
            MqttConnectOptions connOpts = new MqttConnectOptions();
            connOpts.setUserName(username);
            connOpts.setPassword(password.toCharArray());
            connOpts.setCleanSession(true);
            //System.out.println("Connecting to broker: "+broker);
            sampleClient.connect(connOpts);
            //System.out.println("Connected");
            //System.out.println("Publishing message: "+content);
            MqttMessage message = new MqttMessage(content.getBytes());
            message.setQos(qos);
            sampleClient.publish(topic, message);
            //System.out.println("Message published");
            sampleClient.disconnect();
            //System.out.println("Disconnected");
        } catch(MqttException me) {
            //System.out.println("reason "+me.getReasonCode());
            //System.out.println("msg "+me.getMessage());
            //System.out.println("loc "+me.getLocalizedMessage());
            //System.out.println("cause "+me.getCause());
            //System.out.println("excep "+me);
            me.printStackTrace();
        }
    }
}
