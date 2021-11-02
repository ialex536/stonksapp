package com.example.stonks;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ScrollView;


import java.util.ArrayList;


public class InfoActivity extends AppCompatActivity{

    TextView scrollTitle;
    ImageView scrollImage1;
    ImageView scrollImage2;
    ImageView scrollImage3;
    ImageView scrollImage4;
    ImageView scrollImage5;
    TextView scrollText1;
    TextView scrollText2;
    TextView scrollText3;
    TextView scrollText4;
    TextView scrollText5;

    ScrollView scrollView;

    int courseSelected;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayShowCustomEnabled(true);
        LayoutInflater inflater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.actionbar_image, null);
        getSupportActionBar().setCustomView(view);

        //courseSelected is assigned to the int value of courseNum from MainActivity.java
        Intent intent = this.getIntent();
        if (intent != null){
            courseSelected = intent.getIntExtra("courseNum", 1);
        }

            //Assigning View Member Variables to Ids
        scrollView = findViewById(R.id.sv_info_scrollview);
        scrollTitle = findViewById(R.id.tv_info_subtitle);
        scrollImage1 = findViewById(R.id.iv_info_scroll_image1);
        scrollImage2 = findViewById(R.id.iv_info_scroll_image2);
        scrollImage3 = findViewById(R.id.iv_info_scroll_image3);
        scrollImage4 = findViewById(R.id.iv_info_scroll_image4);
        scrollImage5 = findViewById(R.id.iv_info_scroll_image5);
        scrollText1 = findViewById(R.id.tv_info_scroll_text1);
        scrollText2 = findViewById(R.id.tv_info_scroll_text2);
        scrollText3 = findViewById(R.id.tv_info_scroll_text3);
        scrollText4 = findViewById(R.id.tv_info_scroll_text4);
        scrollText5 = findViewById(R.id.tv_info_scroll_text5);

        Button backButton = (Button) findViewById(R.id.btn_info_back);
        Button nextButton = (Button) findViewById(R.id.btn_info_next);

        backButton.setOnClickListener(v -> startNewActivity(0));
        nextButton.setOnClickListener(v -> startNewActivity(1));

        //scrollView.setOnTouchListener(this);

        //TODO: this is kept for debug evidence later in project
        Log.i("Stonks", String.valueOf(courseSelected));
        scrollTitle.setText("Scroll to learn more");

        infoStart();
    }



    //infoStart() is called from onCreate()
    public void infoStart() {
        //TODO: course1Array can be moved to strings.xml as a string array
        //course1Array holds strings for course 1 text
        String[] text1 = {"Investing is the act of allocating capital, usually money, with the expectation of generating an income or profit.\n\n" +
                "Capital can be raised by entrepreneurs, businesses and Governments to meet their various funding requirements. This could include funding a new start-up, an existing business, new product development or to meet Government spending objectives.\n\n" +
                "The financial system plays a key role as an intermediary, facilitating the flow of capital from investors and those seeking to raise or borrow funds. This includes the share market, fund managers and banks.\n\n",

                "An investment in equities is money invested in a company by purchasing the shares of that company. These shares are typically publicly traded on a stock exchange.\n\n" +
                "Investing in shares, gives the holder direct exposure to that company, and its fortunes.\n\nThe investor will receive a return on their investment in the form of capital returns, from any increase (or decrease) in share price and any dividends paid on the shares.",

                "This course will focus on identifying who you are as an investor.\n\nAn investor is anyone who allocates capital with the expectation of making a return in the future.\n\nFor example, in New Zealand, nearly 3 million people have joined KiwiSaver – all of these people are investors. Kiwisaver will be covered in a later course.",

                "What is KiwiSaver?",

                "What are Term Deposits?\n\n" +
                "A term deposit is a special kind of savings account. When you set up a term deposit with a bank, you agree not to withdraw any of your money for a fixed period of time, say 30 days or 5 years. \n",

                "Not everyone feels confident about investing in bonds, shares or other investments, but you can pay a financial expert or organisation to do this for you. This investment option is called a managed fund.\n\n" +
                        "What is a managed fund?\n\nA managed fund is a fund managed by a team of experts who invest your money in a mix of ways, such as shares, bonds or cash.\n\nKiwiSaver investments are a form of managed fund, but there are other options as well.\n"};


        String[] text2 = {"While investing can herald strong returns, and there are some great stories of successful investors such as Warren Buffet, investing is not without risk.\n\nIn addition to the expected profit, part or all of the initial capital can be lost. Generally speaking the higher the return, the higher the risk and vice versa.",

                "Shares can provide attractive returns over the long run, which is the biggest benefit however share prices can also be very volatile in the short run.\n\nShare prices are not only impacted by company specific factors, such as growth in sales and profitability, but also factors outside of their control such as Government policy, interest rates, regulatory approvals and pandemics.\n",

                "An investor profile is a description of what type of investor someone is. Your investor profile can be thought of as your identity and is made up of a range of factors including:",

                "KiwiSaver is a voluntary investment scheme set up by the government to help people get ready for retirement.\n\nPeople who choose to join KiwiSaver typically put money into a KiwiSaver account each time they get paid.\n\nThe payments happen automatically, so it’s a really easy way to put money aside. Investment experts invest the money that is in KiwiSaver accounts so that it grows over time.",


                "If you take your money out before the agreed amount of time, you’ll be charged a penalty such as earning a lower rate of interest. \n\n" +
                "On the upside, a penalty can be a good incentive not to splash out and to only use these funds if you really need them.\n\n",

                "When you invest in a managed fund, you usually buy an investment unit. You can sell these units whenever you want.\n\n" +
                "How much money do you need to invest? You can invest in many managed funds for less than $500.\n\nOnce you’ve invested, you can add much smaller regular payments. These additional payments help you grow your investment over time.\n\n" +
                "Some online platforms enable you to invest at a much lower cost, which means that you can start a managed fund with just a few dollars. These will be covered in a later course.\n"};


        String[] text3 = {"Key principles of investing:\n\n" +
                "1.    Establish a plan. Be specific on your objectives, know your tolerance for risk and timeframe for investing.\n" +
                "2.    Understand what you are investing in. What the company does, how it makes money, how you will get a return on your investment, etc.\n" +
                "3.    Diversification. Don’t put all your eggs in one basket. By investing in a number of different assets, you can increase your risk adjusted return.\n" +
                "4.    Minimise costs. These can have a material impact on the return over the long term.\n" +
                "5.    Maintain a long term perspective and have a disciplined approach.\n",

                "Given the potential volatility, it is important to consider your time horizon (how long you are investing for) such that you can ride out any short-term volatility.\n\nFor example retirees who were invested in shares prior to the Global Financial Crisis in 2007 may not have had the time to make back the amount they lost." +
                        "When investing in shares, you should research the company and sector they operate in to understand the risks.\n\nThe financial and utility sectors have traditionally been considered lower risk and more stable dividend paying, whereas the IT sector has been a growth sector, however generally pay low to no dividends.",

                "-\tAge : Age is important because financial needs and goals change as we grow older. At a young age, investment goals might be to save up for something you desire, while as a young adult an investment goal might be to save for a house, and older still, to save for retirement. \n" +
                "-\tIncome: Income is the money you have coming in on a regular basis from earned or unearned income.\n" +
                "-\tFamily: If a person is struggling to meet the everyday needs of themselves or their whānau, then investing money might not be a good option until they are in a stronger place financially\n\n" +
                "-\tExpected Returns: A return is how much you get back from investing, in other words, how much your investment grows over time (or not!). Obviously, no one wants a negative return, but some people are prepared to take greater risks with the hope that an investment will bring a greater return, while other prefer a safer, more predictable option.\n\n" +
                "-\tTimeframe: How soon will you need the money? The purpose of an investment is to meet a financial goal, so you need to be clear about when you will want to access the money you have invested, along with any returns. The length of an investment also relates to how much risk you are willing to take. Some investments go up and down in value more than others.\n\n" +
                "-\tYour Financial Stability: Sometimes when you invest money, you can’t access it for months or even years. It’s important to be able to cope financially with the ordinary ups and downs of life before you start investing.\n\n" +
                "-\tValues: For some people, choosing an ethical investment is really important, even if it means they make less money from their investments over time. For example, some people don’t want their money invested in fossil fuels, weapons or tobacco because these things don’t align with their values.\n\n" +
                "-\tGoals: For example, how much money you want to get from your investments. Remember that risk and return are related, so seeking a big return from an investment usually involves accepting quite a lot of risk.\n\n",

                "KiwiSaver funds are mainly for retirement, so even though it’s your money, you can’t use it until you turn 65.\n\nAn important exception is that you can use your funds to help buy your first home.\n\nYou can do this if you have been a KiwiSaver investor for at least three years.\n\n",

                " Term deposits usually offer a higher interest rate than most savings accounts. Term deposits won’t make your money grow very fast but they are low risk.\n\n" +
                "Most banks require a minimum deposit, for example $5000. You can’t add to this amount over the term (time period) of the investment. The rate of interest you get is often higher if you invest a larger amount or if you are investing for a longer-term.\n",

                "Like KiwiSaver, if you invest in a managed fund, you can select a fund to match the level of investment risk you’re comfortable with.\n\n" +
                        "For example, shares and property carry more risk than bonds or cash, but can also provide higher returns.\n\nA typical investment mix for an “aggressive” investor will have a higher proportion invested in shares and property than the mix used for a “conservative” investor.\n"};


        String[] text4 = {"Investments can take various different forms, but traditionally are split into the following different types of asset classes:\n\n" +
                "-     Fixed Income (cash, bonds)\n" +
                "-     Stocks (equities)\n" +
                "-     Real Estate\n" +
                "-     Commodities (directly into commodities or indirectly via companies exposed to commodity prices, such as gold miners)\n" +
                "-     Alternative Investments (Private Equity, Venture Capital, digital assets)\n",

                "Remember to diversify. Don’t put all your eggs in one basket. You should invest in a number of different companies across different sectors.\n\n" +
                        "As a first-time investor you may want to consider investing in an Exchange Traded Fund or ETF, which is a listed fund, that itself invests a portfolio of companies and will instantly provide you with diversification.\n\nAlternatively, there are unlisted funds, in which case you will deal directly with the fund manager rather than through a stock exchange, or you can invest directly in the shares of companies, of your own choice.",

                "There are 5 types of investors: Defensive, conservative, balanced, growth, and aggressive. These are defined by the level of risk that an investor is willing to take on. ",

                "If you are employed, you can choose how much of your pay goes into your KiwiSaver account.\n\nThe options are 3%, 4%, 8%, or 10% of your pay.\n\nYou can also make additional voluntary payments.\n\n" +
                        "When you join KiwiSaver, you can choose the level of risk you want to take.\n\nThere are five levels and each one corresponds to a different investor profile. For example, if you want a low level of risk, you might choose a defensive fund. If you want a higher level of risk, with potentially higher returns, you might prefer a growth or aggressive fund.\n",

                "Term deposits are a steady and reliable way to grow your savings. They are a very low-risk form of investment.\n\n" +
                "One risk with a term deposit is that unexpected circumstances may mean that you have to break the agreement.\n\nThis can involve a penalty or you may lose some of the interest you would have earned if you had left the money in the account for the full term.\n",

                "Long-term managed funds usually have higher returns than term deposits, but can go up and down over time. You can earn income from managed funds as well as capital gains.\n\n" +
                "In a managed fund, you pay a manager to administer it and to choose the investments. Fees can vary greatly between different fund managers and between different types of funds.\n\nThese costs will impact on how much money you make, especially in the long term. You pay tax on any profits your managed fund makes.\n"};


        String[] text5 = {"Where to start and how you can invest?\n" +
                "-            Depending on how much money you have you may consider contacting a financial advisor, however if you have limited funds and are just starting out, you could also:\n\n" +
                "-            Invest through a fund, into a broad basket of assets; or\n" +
                "-            Invest in individual assets, and build up a portfolio from inception or over time.\n",

                "It is relatively easy to set up an online account with a broker to buy shares. Investment platforms in New Zealand include: \n" +
                        "-\tSharesies\n" +
                        "-\tHatch\n" +
                        "-\tStake\n",
                "A defensive investor: A defensive investor is someone who doesn’t want their investment to go down in value at any time. They want a low-risk investment that possibly gives low returns but is predictable and stable.\n\n" +
                "A conservative investor: A conservative investor is someone who is willing to take some ups and downs in their investment but still wants to play it safe. A conservative investor often wants to be able to get regular income from their investments.\n\n" +
                "A balanced investor: As the name suggests, a balanced investor is someone who is right in the middle of the investor types. A balanced investor is comfortable with seeing the value of their investments fall a little and they’re aiming for a mid-range long-term return.\n\n" +
                "A growth investor: A growth investor is someone with a long term view who wants fairly high growth and is in a position to wait for it. A growth investor can handle seeing their investment balance fall quite a lot.\n\n" +
                "An aggressive investor: An aggressive investor is someone who is prepared to take some massive downs with the expectation that, over a long period of time, they will make some massive wins.\n\n" +
                "Using these definitions, what kind of investor do you think you are?\n\n",

                "People who invest in KiwiSaver are often surprised at how quickly their money mounts up.\n\nThis is because as well as your own contributions, your employer also contributes at least 3% of your pay.That’s extra money that you don’t get if you’re not a KiwiSaver investor.\n\nThe government also puts money in, up to $521 a year.\n\n" +
                        "When you invest in KiwiSaver, you pay a fee to your fund manager to pay for the investment, management and administration costs of running the scheme. You also pay tax on your investments, which is taken off automatically by your fund manager.\n\n" +
                        "You can learn more about KiwiSaver online, but for now take the Quiz to see how much you’ve learnt!\n",

                "Term deposits don’t make a lot of money, but you can earn more interest on them than you would if you left your money in a normal savings account.\n\n" +
                        "Term deposit interest rates go up and down, but once you have set up a term deposit, the interest rate on that investment won’t change, so you can work out exactly how much you will earn from your investment.\n\n" +
                        "It usually doesn’t cost anything to set up a term deposit, but you will pay tax on any interest you earn.\n\n" +
                        "What have you learnt about term deposits? Take the quiz to see how well you’ve done!",

                "Now that you’re an expert on managed funds, take the quiz to test yourself!"};

        int[] img1 ={R.drawable.asset2xldpi, R.drawable.asset10xldpi, R.drawable.asset7xldpi,  R.drawable.asset18xldpi, R.drawable.asset21xldpi, R.drawable.asset2xldpi};
        int[] img2 ={R.drawable.asset3xldpi, R.drawable.asset18xldpi, R.drawable.img_line_1px,  R.drawable.asset10xldpi, R.drawable.asset10xldpi, R.drawable.asset10xldpi};
        int[] img3 ={R.drawable.asset4xldpi, R.drawable.asset21xldpi, R.drawable.img_line_1px,  R.drawable.asset10xldpi, R.drawable.asset10xldpi, R.drawable.asset10xldpi};
        int[] img4 ={R.drawable.asset5xldpi, R.drawable.asset23xldpi, R.drawable.img_line_1px,  R.drawable.asset10xldpi, R.drawable.asset10xldpi, R.drawable.asset10xldpi};
        int[] img5 ={R.drawable.asset7xldpi, R.drawable.asset17xldpi, R.drawable.img_line_1px,  R.drawable.asset10xldpi, R.drawable.asset10xldpi, R.drawable.asset10xldpi};

        Info infoCourse1 = new Info(img1[courseSelected],img2[courseSelected],img3[courseSelected],img4[courseSelected],img5[courseSelected],text1[courseSelected],text2[courseSelected],text3[courseSelected],text4[courseSelected],text5[courseSelected]);

        displayInfo(infoCourse1);
    }

    public void displayInfo(Info courseInfo) {

        //TODO: Once second course is added, the drawables assigned to view member variables will
        //      need to be done using a parameter of which course is selected
        //Sets View member variables to text and image drawables of the Info object
        scrollImage1.setImageResource(courseInfo.image1Id);
        scrollImage2.setImageResource(courseInfo.image2Id);
        scrollImage3.setImageResource(courseInfo.image3Id);
        scrollImage4.setImageResource(courseInfo.image4Id);
        scrollImage5.setImageResource(courseInfo.image5Id);
        scrollText1.setText(courseInfo.text1);
        scrollText2.setText(courseInfo.text2);
        scrollText3.setText(courseInfo.text3);
        scrollText4.setText(courseInfo.text4);
        scrollText5.setText(courseInfo.text5);
    }

    //onButtonSelected is called when the back/next buttons are clicked and accepts the integer buttonSelected as a parameter
    //if 0 (back button) MainActivity is called, if 1 (next button) QuizActivity is called
    //TODO: when course 2 and quiz 2 are added a a variable to determine which quiz is being called should be passed

    public void startNewActivity(int buttonSelected) {
        if (buttonSelected == 1) {
            Intent intent = new Intent(this, QuizActivity.class);
            intent.putExtra("courseNum", courseSelected);
            startActivity(intent);
        } else if (buttonSelected == 0) {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }
    }
}