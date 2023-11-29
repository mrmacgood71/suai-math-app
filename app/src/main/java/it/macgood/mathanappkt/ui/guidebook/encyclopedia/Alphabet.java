package it.macgood.mathanappkt.ui.guidebook.encyclopedia;

import java.util.Objects;

public class Alphabet {
    private String mLetter;

    public String getLetter() {
        return mLetter;
    }

    public Alphabet(String mLetter) {
        this.mLetter = mLetter;
    }

    public void setLetter(String mLetter) {
        this.mLetter = mLetter;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Alphabet)) return false;
        Alphabet alphabet = (Alphabet) o;
        return Objects.equals(mLetter, alphabet.mLetter);
    }

    @Override
    public int hashCode() {
        return Objects.hash(mLetter);
    }
}
