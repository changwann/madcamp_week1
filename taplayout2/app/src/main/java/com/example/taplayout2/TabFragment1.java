package com.example.taplayout2;

import android.content.ContentProviderOperation;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.OperationApplicationException;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.RemoteException;
import android.os.Vibrator;
import android.provider.ContactsContract;
import android.text.Editable;
import android.text.InputFilter;
import android.text.Spanned;
import android.text.TextWatcher;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
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

    public void ContactAdd(String name, String number){
        new Thread(){
            @Override
            public void run() {

                ArrayList<ContentProviderOperation> list = new ArrayList<>();
                try{
                    list.add(
                            ContentProviderOperation.newInsert(ContactsContract.RawContacts.CONTENT_URI)
                                    .withValue(ContactsContract.RawContacts.ACCOUNT_TYPE, null)
                                    .withValue(ContactsContract.RawContacts.ACCOUNT_NAME, null)
                                    .build()
                    );

                    list.add(
                            ContentProviderOperation.newInsert(ContactsContract.Data.CONTENT_URI)
                                    .withValueBackReference(ContactsContract.Data.RAW_CONTACT_ID, 0)

                                    .withValue(ContactsContract.Data.MIMETYPE, ContactsContract.CommonDataKinds.StructuredName.CONTENT_ITEM_TYPE)
                                    .withValue(ContactsContract.CommonDataKinds.StructuredName.DISPLAY_NAME, name)   //이름

                                    .build()
                    );

                    list.add(
                            ContentProviderOperation.newInsert(ContactsContract.Data.CONTENT_URI)
                                    .withValueBackReference(ContactsContract.Data.RAW_CONTACT_ID, 0)

                                    .withValue(ContactsContract.Data.MIMETYPE, ContactsContract.CommonDataKinds.Phone.CONTENT_ITEM_TYPE)
                                    .withValue(ContactsContract.CommonDataKinds.Phone.NUMBER, number)           //전화번호
                                    .withValue(ContactsContract.CommonDataKinds.Phone.TYPE  , ContactsContract.CommonDataKinds.Phone.TYPE_MOBILE)   //번호타입(Type_Mobile : 모바일)

                                    .build()
                    );
                    /*
                    list.add(
                            ContentProviderOperation.newInsert(ContactsContract.Data.CONTENT_URI)
                                    .withValueBackReference(ContactsContract.Data.RAW_CONTACT_ID, 0)

                                    .withValue(ContactsContract.Data.MIMETYPE, ContactsContract.CommonDataKinds.Email.CONTENT_ITEM_TYPE)
                                    .withValue(ContactsContract.CommonDataKinds.Email.DATA  , "hong_gildong@naver.com")  //이메일
                                    .withValue(ContactsContract.CommonDataKinds.Email.TYPE  , ContactsContract.CommonDataKinds.Email.TYPE_WORK)     //이메일타입(Type_Work : 직장)

                                    .build()
                    );
                    */
                    getActivity().getApplicationContext().getContentResolver().applyBatch(ContactsContract.AUTHORITY, list);  //주소록추가
                    list.clear();   //리스트 초기화
                }catch(RemoteException e){
                    e.printStackTrace();
                }catch(OperationApplicationException e){
                    e.printStackTrace();
                }
            }
        }.start();
    }


    public void mOnContactAdd(View v, String name, String number) {
        if(v.getId()!=R.id.btnContactAdd) { return; }
        ContactAdd(name, number);
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
                String contactName = contacts.get(position).first;
                deleteContact(contactName);
                displayContacts.remove(position);
                contacts.remove(position);
                listView.setAdapter(listViewAdapter);
                Toast.makeText(getActivity().getApplicationContext(), "해당 연락처 삭제 완료", Toast.LENGTH_SHORT).show();
                return true;
            }
        });

        View button = view.findViewById(R.id.btnContactAdd);
        EditText name_edittext = (EditText)view.findViewById(R.id.editText1);
        EditText number_edittext = (EditText)view.findViewById(R.id.editText2);
        // 숫자와 "-"만 입력 가능하게 하는 InputFilter
        InputFilter filter = new InputFilter() {
            @Override
            public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {
                for (int i = start; i < end; i++) {
                    if (!Character.isDigit(source.charAt(i)) && source.charAt(i) != '-') {
                        return "";
                    }
                }
                return null;
            }
        };
        number_edittext.setFilters(new InputFilter[]{filter});

        // 전화번호 형식에 맞게 자동으로 "-" 추가
        number_edittext.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!s.toString().startsWith("010")) {
                    number_edittext.setText("010");
                    number_edittext.setSelection(number_edittext.getText().length());
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
                String input = s.toString();
                String phoneNumber = input.replaceAll("-", "");
                if (phoneNumber.length() > 8) {
                    phoneNumber = phoneNumber.substring(0, 3) + "-" + phoneNumber.substring(3, 7) + "-" + phoneNumber.substring(7);
                } else if (phoneNumber.length() > 3) {
                    phoneNumber = phoneNumber.substring(0, 3) + "-" + phoneNumber.substring(3);
                }

                if (!input.equals(phoneNumber)) {
                    number_edittext.setText(phoneNumber);
                    number_edittext.setSelection(phoneNumber.length());
                }
            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ContactAdd(name_edittext.getText().toString(), number_edittext.getText().toString());
                Vibrator vibrator = (Vibrator) getActivity().getSystemService(Context.VIBRATOR_SERVICE);
                vibrator.vibrate(100);
                displayContacts.add(name_edittext.getText().toString() + ": " + number_edittext.getText().toString());
                Collections.sort(displayContacts);
                contacts.add(new Pair<>(name_edittext.getText().toString(), number_edittext.getText().toString()));
                Collections.sort(contacts, new Comparator<Pair<String, String>>() {
                    @Override
                    public int compare(Pair<String, String> o1, Pair<String, String> o2) {
                        return o1.first.compareTo(o2.first);
                    }
                });
                listView.setAdapter(listViewAdapter);
                Toast.makeText(getActivity().getApplicationContext(), "연락처 추가 완료", Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }
}