package ru.sadykov.link.baseservice.service.impl;

import org.springframework.stereotype.Service;
import ru.sadykov.link.baseservice.service.ShortLinkCreatorService;

@Service
public class ShortLinkCreatorServiceImpl implements ShortLinkCreatorService {

    @Override
    public String convertFullLinkToShort(String fullLink) {
        char[] ripperString = ripperString(fullLink);
        char[] upChars = upChars(ripperString);
        char[] shuffleChars = shuffleString(upChars, 0, ripperString.length - 1);
        String shortLink;
        if (shuffleChars.length > 10) {
            shortLink = String.valueOf(shuffleChars).substring(0, 9);
        } else {
            shortLink = String.valueOf(shuffleChars);
        }
        return shortLink;
    }

    private char[] upChars(char[] chars) {
        for (int i = 0; i < chars.length; i += 2) {
            chars[i] = Character.toUpperCase(chars[i]);
        }
        return chars;
    }

    private char[] ripperString(String fullLink) {
        String[] splitOne = fullLink.split("//");
        String[] splitTwo = splitOne[1].split("/");
        String url = null;
        if (splitTwo[0].startsWith("www")){
            url = splitTwo[0].substring(4);
        } else {
            url = splitTwo[0];
        }
        return url.toCharArray();
    }

    private char[] shuffleString(char[] chars, int lowBound, int heightBound) {
        if (heightBound != lowBound) {
            int middleBound = (heightBound + lowBound) / 2;
            shuffleString(chars, lowBound, middleBound);
            shuffleString(chars, middleBound + 1, heightBound);
            shuffle(chars, lowBound, heightBound);
        }
        return chars;
    }

    private void shuffle(char[] chars, int lowBound, int heightBound) {
        char tmp = chars[lowBound];
        chars[lowBound] = chars[heightBound];
        chars[heightBound] = tmp;
    }
}
