package com.example.stonks;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AlertDialog;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Quiz extends the AppCompatActivity class, allowing the use of FindViewById() and the action bar on older android devices
 * On creation, the member variables are assigned and startNewGame() is called
 * Questions are instantiated in startNewGame()
 *
 * TODO: Change the helpButton alert dialog message
 */

public class QuizActivity extends AppCompatActivity {

    int currentQuestionIndex;
    int totalCorrect;
    int totalQuestions;
    ArrayList<Question> questions;
    int courseSelected;

    // Declare View member variables
    ImageView questionImageView;
    TextView questionTextView;
    TextView questionsRemainingTextView;
    Button answer0Button;
    Button answer1Button;
    Button answer2Button;
    Button answer3Button;
    Button submitButton;
    ImageButton helpButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Shows app icon in ActionBar
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayShowCustomEnabled(true);
        LayoutInflater inflater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.actionbar_image, null);
        getSupportActionBar().setCustomView(view);

        setContentView(R.layout.activity_quiz);

        // Assigns View member variables to ViewIds
        questionImageView = findViewById(R.id.iv_quiz_question_image);
        questionTextView = findViewById(R.id.tv_quiz_question_title);
        questionsRemainingTextView = findViewById(R.id.tv_quiz_questions_remaining_count);
        answer0Button = findViewById(R.id.btn_quiz_answer_0);
        answer1Button = findViewById(R.id.btn_quiz_answer_1);
        answer2Button = findViewById(R.id.btn_quiz_answer_2);
        answer3Button = findViewById(R.id.btn_quiz_answer_3);
        submitButton = findViewById(R.id.btn_quiz_submit_answer);
        helpButton = findViewById(R.id.imgbtn_quiz_help);

        // onClickListener for each answer Button
        answer0Button.setOnClickListener(v -> onAnswerSelected(0));
        answer1Button.setOnClickListener(v -> onAnswerSelected(1));
        answer2Button.setOnClickListener(v -> onAnswerSelected(2));
        answer3Button.setOnClickListener(v -> onAnswerSelected(3));

        // onClickListener for the submitButton
        submitButton.setOnClickListener(v -> onAnswerSubmission());

        // onClickListener for the info button
        helpButton.setOnClickListener(v -> alertDialogBuilder());

        // Retrieves the Course Selected integer from InfoActivity
        Intent intent = this.getIntent();
        if (intent != null) {
            courseSelected = intent.getIntExtra("courseNum", 1);
        }

        // calls startNewGame() within onCreate()
        startNewGame();
    }

    // Sets View member variables to question drawables of the question object passed as a parameter
    public void displayQuestion(Question question) {
        questionImageView.setImageResource(question.imageId);
        questionTextView.setText(question.questionText);
        answer0Button.setText(question.answer0);
        answer1Button.setText(question.answer1);
        answer2Button.setText(question.answer2);
        answer3Button.setText(question.answer3);
    }

    // Sets the questionsRemaining textView to reflect the number of questions in ArrayList questions()
    public void displayQuestionsRemaining(int questionsRemaining) {
        questionsRemainingTextView.setText(String.valueOf(questionsRemaining));
    }

    // Redundant method that indicates to the user currentQuestion.playerAnswer with a tick symbol
    // answerSelected is passed by the respective button's OnClickListener
    public void onAnswerSelected(int answerSelected) {
        Question currentQuestion = getCurrentQuestion();
        currentQuestion.playerAnswer = answerSelected;
        answer0Button.setText(currentQuestion.answer0);
        answer1Button.setText(currentQuestion.answer1);
        answer2Button.setText(currentQuestion.answer2);
        answer3Button.setText(currentQuestion.answer3);
        if (answerSelected == 0) {
            answer0Button.setText("✔" + currentQuestion.answer0);
        } else if (answerSelected == 1) {
            answer1Button.setText("✔" + currentQuestion.answer1);
        } else if (answerSelected == 2) {
            answer2Button.setText("✔" + currentQuestion.answer2);
        } else {
            answer3Button.setText("✔" + currentQuestion.answer3);
        }

    }

    // onAnswerSubmission is called by the submitButton onClickListener
    void onAnswerSubmission() {

        // totalCorrect is increased if the answer is correct and the currentQuestion is removed from questions()
        // If playerAnswer is -1 the player has not selected an answer and onAnswerSubmission returns
        Question currentQuestion = getCurrentQuestion();
        if (currentQuestion.isCorrect()) {
            totalCorrect++;
            // TODO: set buttons to red or green based on currentQuestion.isCorrect
        } else if (currentQuestion.playerAnswer == -1) {
            return;
        }
        questions.remove(currentQuestion);

        // The question remaining count is updated to reflect the decrease in questions remaining after an answer is submitted
        displayQuestionsRemaining(questions.size());

        // If there are no questions remaining, the final message presents to the user showing their questions correct
        // The option to play again calls startNewGame()
        // If there are questions remaining chooseNewQuestion() chooses from the remaining questions in the list
        if (questions.size() == 0) {
            String gameOverMessage = getGameOverMessage(totalCorrect, totalQuestions);

            AlertDialog.Builder gameOverDialogBuilder = new AlertDialog.Builder(QuizActivity.this);
            gameOverDialogBuilder.setCancelable(false);
            gameOverDialogBuilder.setTitle("Game Over!");
            gameOverDialogBuilder.setMessage(gameOverMessage);

            //TODO: Another button can be added to go straight to the next course
            gameOverDialogBuilder.setPositiveButton("Next Lesson!", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    startNewActivity(1);
                }
            });

            gameOverDialogBuilder.create().show();
        } else {
            chooseNewQuestion();

            displayQuestion(getCurrentQuestion());
        }
    }

    // startNewGame is called from OnCreate() when unquote is run
    void startNewGame() {
        questions = new ArrayList<>();

        // Questions are instantiated and the drawables are provided for each Question object
        Question question0 = new Question(R.drawable.img_quiz_0_x, "What, fundamentally, is investing?", "Getting Rich quickly with no risk or losses", "Buying Stocks and making money", "The allocation of capital to receive an income or profit", "Buying Items on sale", 2);
        Question question1 = new Question(R.drawable.img_quiz_1_x, "Which one is not an investment?", "Public shares, or stocks", "Electronics (laptops and phones)", "Property, such as houses or property funds", "Commodities like Oil and Gold", 1);
        Question question2 = new Question(R.drawable.img_quiz_2_x, "What generally comes with higher expected returns?", "The risk of higher losses", "A guarantee to make more money", "They can only be made by professionals", "Nothing", 0);
        Question question3 = new Question(R.drawable.img_quiz_3_x, "What is diversification?", "Higher returns", "Many people from different backgrounds", "Investing a large amount of capital", "Allocating capital across multiple different investments", 3);

        Question question4 = new Question(R.drawable.img_quiz_5mdpi, "What are equities?", "Debts of publicly traded companies", "Small silver coins", "The currency used on the stock market", "Shares of a publicly traded company", 3);
        Question question5 = new Question(R.drawable.img_quiz_6mdpi, "Shares are generally ______ in the short run, but provide ______ in the long run.", "Stable, losses", "Affordable, expensive", "Volatile, returns", "2", 2);
        Question question6 = new Question(R.drawable.img_quiz_7mdpi, "Which is not a factor that affects share prices?", "Government Policy", "Interest rates", "Pandemics", "Your Bank account balance", 3);
        Question question7 = new Question(R.drawable.img_quiz_8mdpi, "Which is not an important factor to consider when investing?", "Your time horizon", "What your peers are investing in", "Risk", "Past performance", 3);

        Question question8 = new Question(R.drawable.img_quiz_8mdpi, "What is an investor profile?", "The name, contact details, and address of an investor", "An investor’s social media account", "A description of what category an investor falls into", "A profile picture for an investor", 2);
        Question question9 = new Question(R.drawable.img_quiz_8mdpi, "What is not a factor that shapes an investor profile?", "Hair colour", "Financial Status", "Investment Timeframe", "Ethics and morals", 0);
        Question question10 = new Question(R.drawable.img_quiz_8mdpi, "What determines what kind of investor someone is?", "The amount of money that they have", "Their risk tolerance and investment timeframe", "Their gender", "How old they are", 1);
        Question question11 = new Question(R.drawable.img_quiz_8mdpi, "Which one of these in not one of the five main types of investors?", "Aggressive", "Conservative", "Defensive", "Expensive", 3);

        Question question12 = new Question(R.drawable.img_quiz_8mdpi, "What is the level of risk of KiwiSaver?", "You can choose one of the five levels", "KiwiSaver is high-risk", "KiwiSaver is low-risk", "There is no risk involved", 0);
        Question question13 = new Question(R.drawable.img_quiz_8mdpi, "What percentages can you choose to invest out of your pay?", "1%, 2%, 3%, 4%, 5%", "1%, 2%, 4%, 8%, 16%", "3%, 4%, 8%, 10%, 10%+", "0%, 100%", 2);
        Question question14 = new Question(R.drawable.img_quiz_8mdpi, "What are the fees associated with KiwiSaver?", "There are no fees", "The fees depend on what fund you choose, plus tax on earnings", "There is a $5 per month fee", "There is only tax", 1);
        Question question15= new Question(R.drawable.img_quiz_8mdpi, "When can you withdraw your KiwiSaver money?", "You can withdraw it in whenever you want", "You can withdraw it when you retire", "You can withdraw it to buy a house", "You can withdraw it to buy a first home or when you retire", 3);

        Question question16 = new Question(R.drawable.img_quiz_8mdpi, "What is important about the timeframe of term deposits?", "They are flexible so you can withdraw at any time", "The money is not withdrawn until retirement", "They are all 1-year timeframes", "The term is fixed and you cannot withdraw until the term is up", 3);
        Question question17 = new Question(R.drawable.img_quiz_8mdpi, "How much money is the usual minimum amount for a term deposit?", "$1", "$5000", "$500", "%5", 1);
        Question question18 = new Question(R.drawable.img_quiz_8mdpi, "What fees are associated with term deposits?", "They charge high management fees", "There are no fees or taxes", "Typically just tax on earnings", "They depend on the length of the term", 2);
        Question question19= new Question(R.drawable.img_quiz_8mdpi, "What is the relative risk of term deposits?", "Low risk", "Medium risk", "High risk", "No risk", 0);

        Question question20 = new Question(R.drawable.img_quiz_8mdpi, "What is the timeframe of managed funds?", "You can buy and sell units in the fund at any time", "They are a fixed-term investment", "You can only withdraw at retirement", "There are set times that you can buy units", 0);
        Question question21 = new Question(R.drawable.img_quiz_8mdpi, "What is the minimum investment?", "A few dollars", "$1,000", "$10,000", "500", 0);
        Question question22 = new Question(R.drawable.img_quiz_8mdpi, "What is the risk of a managed fund?", "They are high-risk investments", "It depends what fund you choose", "They are low-risk", "It depends on how much you invest", 1);
        Question question23= new Question(R.drawable.img_quiz_8mdpi, "What are the fees associated with managed funds?", "They have high fees", "The fees vary based on the fund", "The fees vary, but are usually higher as they are managed", "There are no fees", 2);


        Question[] questionCourse1 = {question0, question1, question2, question3};
        Question[] questionCourse2 = {question4, question5, question6, question7};
        Question[] questionCourse3 = {question8, question9, question10, question11};
        Question[] questionCourse4 = {question12, question13, question14, question15};
        Question[] questionCourse5 = {question16, question17, question18, question19};
        Question[] questionCourse6 = {question20, question21, question22, question23};

        // Question objects are added to questions()
        // TODO: this should be done using a loop // Done
        if (courseSelected == 0) {
            Collections.addAll(questions, questionCourse1);
        } else if (courseSelected == 1) {
            Collections.addAll(questions, questionCourse2);
        } else if (courseSelected == 2) {
            Collections.addAll(questions, questionCourse3);
        } else if (courseSelected == 3) {
            Collections.addAll(questions, questionCourse4);
        } else if (courseSelected == 4) {
            Collections.addAll(questions, questionCourse5);
        } else if (courseSelected == 5) {
            Collections.addAll(questions, questionCourse6);
        }

        //resets totalCorrect and totalQuestions each time startNewGame() is called
        totalCorrect = 0;
        totalQuestions = questions.size();

        //calls chooseNewQuestion to select the firstQuestion
        Question firstQuestion = chooseNewQuestion();

        // Updates the questions remaining count at the start of the game by calling displayQuestionsRemaining()
        displayQuestionsRemaining(questions.size());

        // calls the displayQuestion method to display the newly selected question to the user
        displayQuestion(firstQuestion);
    }

    // alertDialogBuilder is called when the user presses helpButton
    // an alert dialog is presented to the user to briefly inform them of how to use quizActivity
    public void alertDialogBuilder() {
        AlertDialog.Builder disclaimerDialogBuilder = new AlertDialog.Builder(QuizActivity.this);
        disclaimerDialogBuilder.setCancelable(false);
        disclaimerDialogBuilder.setTitle("How to Play?\n\n");
        disclaimerDialogBuilder.setMessage("1) The quiz will ask you four questions! Pay attention to the questions shown under the image. \n\n" +
                "2) Select the right answer – Don’t worry, you have as much time as you need to choose.\n\n" +
                "3) Submit your answers using the ‘Submit’ button at the bottom right. \n\n" +
                "4) Done! See how many questions you got right! You can retake this quiz at any time. \n\n");
        //If the user selects the accept button the alert dialog is dismissed
        disclaimerDialogBuilder.setPositiveButton("OKAY", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                dialog.dismiss();
            }
        });
        disclaimerDialogBuilder.create().show();
    }

    // sets currentQuestionIndex to the ArrayList index value of the current question in questions()
    // returns the current Question object
    Question chooseNewQuestion() {
        currentQuestionIndex = generateRandomNumber(questions.size());
        return questions.get(currentQuestionIndex);
    }

    // returns randNum between 0 and max parameter assigned
    int generateRandomNumber(int max) {
        double randomNumber = Math.random();
        double result = max * randomNumber;
        return (int) result;
    }

    // returns the current Question Object
    Question getCurrentQuestion() {
        return questions.get(currentQuestionIndex);
    }

    // takes totalCorrect and totalQuestions as parameters and returns a game over string
    String getGameOverMessage(int totalCorrect, int totalQuestions) {
        if (totalCorrect == totalQuestions) {
            return "You got all " + totalQuestions + " right! You won!";
        } else {
            return "You got " + totalCorrect + " right out of " + totalQuestions + ". Better luck next time!";
        }
    }

    public void startNewActivity(int activityNum) {
        if (activityNum == 1) {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }
    }
}