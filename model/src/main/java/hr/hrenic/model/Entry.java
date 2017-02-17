package hr.hrenic.model;

import android.os.Parcel;
import android.os.Parcelable;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents one entry that is sent from one app to another.
 */
public class Entry implements Parcelable {

    public static final String EXTRA = "hr.hrenic.extra.ENTRIES";
    public static final String ACTION_SEND = "hr.hrenic.action.SEND";
    public static final String ACTION_SEND_MULTI = "hr.hrenic.action.SEND_MULTI";

    private static final ArrayList<Entry> DUMMY_ENTRIES;

    static {
        DUMMY_ENTRIES = new ArrayList<>();
        DUMMY_ENTRIES.add(new Entry(1, "Jedan"));
        DUMMY_ENTRIES.add(new Entry(2, "Dva"));
        DUMMY_ENTRIES.add(new Entry(3, "Tri"));
        DUMMY_ENTRIES.add(new Entry(4, "Četiri"));
        DUMMY_ENTRIES.add(new Entry(5, "Pet"));
    }

    /**
     * This is a dummy function and returns data for testing purposes
     *
     * @return dummy data
     */
    public static ArrayList<Entry> getEntries() {
        return DUMMY_ENTRIES;
    }

    /*
        Keys for JSON mapping
     */
    private static final String ID_KEY = "id";
    private static final String NAME_KEY = "name";

    /*
        Member variables.
        To add more, modify:
            - constructors
            - entry.toJSON
            - entry.writeToParcel

     */
    private int id;
    private String name;

    /**
     * Default constructor. Takes in all parameters
     *
     * @param id   entry id
     * @param name entry name
     */
    private Entry(int id, String name) {
        this.id = id;
        this.name = name;
    }

    /**
     * Read entry from parcel
     *
     * @param in parcel to read from
     */
    private Entry(Parcel in) {
        this(in.readInt(), in.readString());
    }

    /**
     * Read entry from JSON object
     *
     * @param jsonObject json object, must have all member variables
     *
     * @throws JSONException
     */
    public Entry(JSONObject jsonObject) throws JSONException {
        this(jsonObject.getInt(ID_KEY), jsonObject.getString(NAME_KEY));
    }

    /**
     * Wrap this entry into an JSON object
     *
     * @return wrapped JSON object
     * @throws JSONException
     */
    public JSONObject toJSON() throws JSONException {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put(ID_KEY, id);
        jsonObject.put(NAME_KEY, name);
        return jsonObject;
    }

    /**
     * Unwrap an JSON array containing JSON objects (entries) into pure entries
     *
     * @param jsonArray array
     *
     * @return list of entries
     * @throws JSONException
     */
    public static List<Entry> fromJSON(JSONArray jsonArray) throws JSONException {
        int size = jsonArray.length();
        List<Entry> entries = new ArrayList<>(size);

        for (int i = 0; i < size; i++) {
            JSONObject jsonObject = jsonArray.getJSONObject(i);
            entries.add(new Entry(jsonObject));
        }

        return entries;
    }

    /**
     * Wrap a list of entries into a JSON array
     *
     * @param entries entries we want to wrap
     *
     * @return JSON array
     * @throws JSONException
     */
    public static JSONArray toJSON(List<Entry> entries) throws JSONException {
        JSONArray jsonArray = new JSONArray();
        for (Entry entry : entries) {
            jsonArray.put(entry.toJSON());
        }
        return jsonArray;
    }

    /**
     * @return entry id
     */
    public int getId() {
        return id;
    }

    /**
     * @return entry name
     */
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
