package com.example.uts_lec;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\b\u0010\f\u001a\u00020\rH\u0002J\u0012\u0010\u000e\u001a\u00020\r2\b\u0010\u000f\u001a\u0004\u0018\u00010\u0010H\u0014R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\nX\u0082.\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0011"}, d2 = {"Lcom/example/uts_lec/MenuActivity;", "Landroidx/activity/ComponentActivity;", "()V", "auth", "Lcom/google/firebase/auth/FirebaseAuth;", "databaseRef", "Lcom/google/firebase/database/DatabaseReference;", "postCaptionView", "Landroid/widget/TextView;", "postImageView", "Landroid/widget/ImageView;", "profileImage", "loadUserData", "", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "app_debug"})
public final class MenuActivity extends androidx.activity.ComponentActivity {
    private android.widget.ImageView postImageView;
    private android.widget.TextView postCaptionView;
    private android.widget.ImageView profileImage;
    private com.google.firebase.database.DatabaseReference databaseRef;
    private com.google.firebase.auth.FirebaseAuth auth;
    
    public MenuActivity() {
        super(0);
    }
    
    @java.lang.Override
    protected void onCreate(@org.jetbrains.annotations.Nullable
    android.os.Bundle savedInstanceState) {
    }
    
    private final void loadUserData() {
    }
}