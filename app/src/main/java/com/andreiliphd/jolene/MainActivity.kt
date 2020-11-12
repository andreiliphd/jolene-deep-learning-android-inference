package com.andreiliphd.jolene

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import org.json.JSONObject


class MainActivity : AppCompatActivity() {
    internal lateinit var get_result: Button
    internal lateinit var output_result: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        output_result = findViewById(R.id.output_result)
        get_result = findViewById(R.id.get_result)
        get_result.setOnClickListener { view ->
            makeCall()
        }

    }
    fun makeCall() {
        Log.d("Making Call", "Call was made!")
        val url = "https://api.graphdatabase.ru"
        val json: String = "{\n" +
                "  \"meta\": {\n" +
                "    \"locale\": \"ru-RU\",\n" +
                "    \"timezone\": \"UTC\",\n" +
                "    \"client_id\": \"ru.yandex.searchplugin/7.16 (none none; android 4.4.2)\",\n" +
                "    \"interfaces\": {\n" +
                "      \"screen\": {},\n" +
                "      \"payments\": {},\n" +
                "      \"account_linking\": {}\n" +
                "    }\n" +
                "  },\n" +
                "  \"session\": {\n" +
                "    \"message_id\": 0,\n" +
                "    \"session_id\": \"de00d7e0-db59-4d7d-9451-d8681597e46b\",\n" +
                "    \"skill_id\": \"7abb8596-49d7-41aa-9cce-1d92aa9d6cd1\",\n" +
                "    \"user\": {\n" +
                "      \"user_id\": \"722F448CC3B612AD9CFE53B345AF7EBF741E94001A4D79158FAA9D8A772A9DFA\"\n" +
                "    },\n" +
                "    \"application\": {\n" +
                "      \"application_id\": \"EF9A5B456A666137815ECA87B1593153783B50661FF2B4DD5BAD25D25595056C\"\n" +
                "    },\n" +
                "    \"user_id\": \"EF9A5B456A666137815ECA87B1593153783B50661FF2B4DD5BAD25D25595056C\",\n" +
                "    \"new\": false\n" +
                "  },\n" +
                "  \"request\": {\n" +
                "    \"command\": \"\",\n" +
                "    \"original_utterance\": \"\",\n" +
                "    \"nlu\": {\n" +
                "      \"tokens\": [],\n" +
                "      \"entities\": [],\n" +
                "      \"intents\": {}\n" +
                "    },\n" +
                "    \"markup\": {\n" +
                "      \"dangerous_context\": false\n" +
                "    },\n" +
                "    \"type\": \"SimpleUtterance\"\n" +
                "  },\n" +
                "  \"version\": \"1.0\"\n" +
                "}\n"
        val queue = Volley.newRequestQueue(this)

        val jsonObjectRequest = JsonObjectRequest(Request.Method.POST, url, JSONObject(json),
                Response.Listener { response ->
                    output_result.text = "Ответ сервера: %s".format(response.getJSONObject("response").getString("text"))
                },
                Response.ErrorListener { error ->
                    output_result.text = "Ответ сервера: %s".format(error.toString())
                }
        )
        queue.add(jsonObjectRequest);
    }
}