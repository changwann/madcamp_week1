package com.example.taplayout2;

import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;
import java.util.List;

public class TabFragment1 extends Fragment {
    private void deleteContact(String name) {
        ContentResolver contentResolver = getActivity().getContentResolver();

        // 연락처를 찾기 위해 쿼리를 수행합니다.
        Cursor cursor = contentResolver.query(ContactsContract.Contacts.CONTENT_URI, null,
                ContactsContract.Contacts.DISPLAY_NAME + " = ?",
                new String[]{name}, null);

        if (cursor != null && cursor.moveToFirst()) {
            // 컬럼 인덱스를 가져옵니다.
            int columnIndex = cursor.getColumnIndex(ContactsContract.Contacts._ID);

            // 컬럼 인덱스가 유효한지 확인합니다.
            if (columnIndex != -1) {
                // 연락처 ID를 가져옵니다.
                String id = cursor.getString(columnIndex);

                // 연락처 데이터와 연락처 ID를 사용하여 연락처를 삭제합니다.
                contentResolver.delete(ContactsContract.RawContacts.CONTENT_URI,
                        ContactsContract.RawContacts.CONTACT_ID + " = ?",
                        new String[]{id});

                contentResolver.delete(ContactsContract.Data.CONTENT_URI,
                        ContactsContract.Data.CONTACT_ID + " = ?",
                        new String[]{id});

                //Toast.makeText(getActivity(), "연락처가 삭제되었습니다.", Toast.LENGTH_SHORT).show();
            }
        }

        if (cursor != null) {
            cursor.close();
        }
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment1, container, false);

        List<Pair<String, String>> contacts = ContactUtils.getContactNamesAndNumbers(getActivity());
        List<String> displayContacts = new ArrayList<>();

        for (Pair<String, String> contact : contacts) {
            displayContacts.add(contact.first + ": " + contact.second);
        }

        ListView listView = (ListView) view.findViewById(R.id.listView);

        ArrayAdapter<String> listViewAdapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, displayContacts);

        listView.setAdapter(listViewAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getActivity().getApplicationContext(), "전화 화면 띄우는 중", Toast.LENGTH_SHORT).show();
                String tel = "tel:"+contacts.get(position).second;
                startActivity(new Intent("android.intent.action.DIAL", Uri.parse(tel)));
            }
        });
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getActivity().getApplicationContext(), "해당 연락처 삭제 중", Toast.LENGTH_SHORT).show();
                String contactName = contacts.get(position).first;
                deleteContact(contactName);
                displayContacts.remove(position); // 얘가 무슨 역할을 하지? 바로 새로고침은 안되고 다른 탭 들어갔다 오면 작동하네.
                Toast.makeText(getActivity().getApplicationContext(), "해당 연락처 삭제 완료", Toast.LENGTH_SHORT).show();
                return true;
            }
        });

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }
}