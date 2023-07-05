package com.example.taplayout2;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.provider.ContactsContract;
import android.util.Pair;

import java.util.ArrayList;
import java.util.List;

public class ContactUtils {

    public static List<Pair<String, String>> getContactNamesAndNumbers(Context context) {
        ContentResolver contentResolver = context.getContentResolver();

        // Query contact names and numbers together
        Cursor cursor = contentResolver.query(
                ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                null,
                null,
                null,
                ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME + " ASC"
        );

        List<Pair<String, String>> contacts = new ArrayList<>();

        if (cursor != null) {
            int displayNameIndex = cursor.getColumnIndexOrThrow(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME);
            int numberIndex = cursor.getColumnIndexOrThrow(ContactsContract.CommonDataKinds.Phone.NUMBER);

            while (cursor.moveToNext()) {
                String name = cursor.getString(displayNameIndex);
                String number = cursor.getString(numberIndex);
                contacts.add(new Pair<>(name, number));
            }

            cursor.close();
        }

        return contacts;
    }
}
