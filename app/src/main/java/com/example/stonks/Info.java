package com.example.stonks;

public class Info {

    int image1Id;
    int image2Id;
    int image3Id;
    int image4Id;
    int image5Id;
    String text1;
    String text2;
    String text3;
    String text4;
    String text5;

    public Info(int image1Identifier,
                    int image2Identifier,
                    int image3Identifier,
                    int image4Identifier,
                    int image5Identifier,
                    String textOne,
                    String textTwo,
                    String textThree,
                    String textFour,
                    String textFive) {

        image1Id = image1Identifier;
        image2Id = image2Identifier;
        image3Id = image3Identifier;
        image4Id = image4Identifier;
        image5Id = image5Identifier;
        text1 = textOne;
        text2 = textTwo;
        text3 = textThree;
        text4 = textFour;
        text5 = textFive;

    }

}
