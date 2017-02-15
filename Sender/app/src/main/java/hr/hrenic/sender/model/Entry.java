package hr.hrenic.sender.model;

import android.os.Parcel;
import android.os.Parcelable;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by fhrenic on 15/02/2017.
 */
public class Entry implements Parcelable {

    private static final String ID_KEY = "id";
    private static final String NAME_KEY = "name";

    private int id;
    private String name;

    public Entry(int id, String name) {
        this.id = id;
        this.name = name;
    }

    private Entry(Parcel in) {
        id = in.readInt();
        name = in.readString();
    }

    public Entry(JSONObject json) throws JSONException {
        this(json.getInt(ID_KEY), json.getString(NAME_KEY));
    }

    public JSONObject toJSON() throws JSONException {
        JSONObject json = new JSONObject();
        json.put(ID_KEY, id);
        json.put(NAME_KEY, name);
        return json;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    /*
        PARCELABLE
     */

    public static final Creator<Entry> CREATOR = new Creator<Entry>() {
        @Override
        public Entry createFromParcel(Parcel in) {
            return new Entry(in);
        }

        @Override
        public Entry[] newArray(int size) {
            return new Entry[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(name);
    }
}
