package com.example.uts_lec;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000^\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\b\u0010\u0019\u001a\u00020\u001aH\u0002J\"\u0010\u001b\u001a\u00020\u001a2\u0006\u0010\u001c\u001a\u00020\u00042\u0006\u0010\u001d\u001a\u00020\u00042\b\u0010\u001e\u001a\u0004\u0018\u00010\u001fH\u0014J\u0012\u0010 \u001a\u00020\u001a2\b\u0010!\u001a\u0004\u0018\u00010\"H\u0014J\b\u0010#\u001a\u00020\u001aH\u0002J\b\u0010$\u001a\u00020\u001aH\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082D\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u000eX\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\nX\u0082.\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u0010\u001a\u0004\u0018\u00010\u0011X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\fX\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\fX\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\bX\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0015\u001a\u00020\u0016X\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0017\u001a\u00020\u0018X\u0082.\u00a2\u0006\u0002\n\u0000\u00a8\u0006%"}, d2 = {"Lcom/example/uts_lec/EditAccountActivity;", "Landroidx/appcompat/app/AppCompatActivity;", "()V", "PICK_IMAGE_REQUEST", "", "auth", "Lcom/google/firebase/auth/FirebaseAuth;", "backButton", "Landroid/widget/ImageView;", "birthdayTextView", "Landroid/widget/TextView;", "confirmPasswordInput", "Landroid/widget/EditText;", "databaseRef", "Lcom/google/firebase/database/DatabaseReference;", "emailTextView", "imageUri", "Landroid/net/Uri;", "nameInput", "passwordInput", "profileImage", "saveButton", "Landroid/widget/Button;", "storageRef", "Lcom/google/firebase/storage/StorageReference;", "loadUserData", "", "onActivityResult", "requestCode", "resultCode", "data", "Landroid/content/Intent;", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "openGallery", "saveChanges", "app_debug"})
public final class EditAccountActivity extends androidx.appcompat.app.AppCompatActivity {
    private android.widget.EditText nameInput;
    private android.widget.TextView birthdayTextView;
    private android.widget.TextView emailTextView;
    private android.widget.EditText passwordInput;
    private android.widget.EditText confirmPasswordInput;
    private android.widget.Button saveButton;
    private android.widget.ImageView backButton;
    private android.widget.ImageView profileImage;
    private com.google.firebase.database.DatabaseReference databaseRef;
    private com.google.firebase.auth.FirebaseAuth auth;
    private com.google.firebase.storage.StorageReference storageRef;
    private final int PICK_IMAGE_REQUEST = 1;
    @org.jetbrains.annotations.Nullable
    private android.net.Uri imageUri;
    
    public EditAccountActivity() {
        super();
    }
    
    @java.lang.Override
    protected void onCreate(@org.jetbrains.annotations.Nullable
    android.os.Bundle savedInstanceState) {
    }
    
    private final void loadUserData() {
    }
    
    private final void openGallery() {
    }
    
    @java.lang.Override
    protected void onActivityResult(int requestCode, int resultCode, @org.jetbrains.annotations.Nullable
    android.content.Intent data) {
    }
    
    private final void saveChanges() {
    }
}