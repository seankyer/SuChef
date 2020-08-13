<h1>Su Chef</h1>
<b></b>
<h5>What will the application do?</h5>
This application will be a cooking assistant for any novice cook. It will take user defined recipes
and present them step by step to the cook in sequential order only once the cook is ready to move on to the next stage.
At the beginning of a cooking session, it will ensure that the cook has the proper ingredients as well as the amounts
needed. The app would then present each step onto the screen, holding there until the user prompts the app to
proceed to the next step. For steps where a cooking timer may be needed, the app will automatically que up the timer 
and
alert the cook when the timer completes.
<h5>Who will use it?</h5>
Individuals such as myself who are interested in learning to cook but want someone to walk them through the steps 
of a recipe. Users would be able to write recipes for other users to cook from, being guided through them with help of 
the app. This application is specially useful for people like to be handheld through each step and don't want to be 
confused by an entire recipe all at once. It will also be of use to forgetful people who don't check that they have all 
the required ingredients before beginning their cooking session.
<h5>Why is this project of interest to you?</h5>
I personally enjoy cooking, but have little experience and knowledge of the craft. I feel I learn and remember recipes 
best when I have someone walk me through the steps. Having an automated assistant would allow myself and people 
like me, to feel directed in the cooking process without having to rely on an actual human.
<h1>User Stories</h1>

- As a user, I add write a new recipe into the app. I create the name, ingredients and instructions, 
adding this recipe to Recipes.
- As a user, I add the required ingredients, amounts and units to IngredientsList. These corresponding ingredients are
 added to the Recipe which is then added to Recipes.
- As a user, I have recipeReader read me my recipes as I cook. If I come to a step that requires a timer, the app will 
automatically que one up for me (based on what I specified during recipeWriter) and will notify me when the time
expires.
- As a user, I save the recipe to my recipe list and from Recipes, choose the meal I want to be guided through.
- As a user, my RecipesList automatically saves as I add Recipes
- As a user, next time I start the program, my RecipesList will be the same RecipesList as last session

<h1>Instructions to Grader</h1>

- You can generate the first required event by clicking 'Write new Recipe' button. This will create a new recipe and 
 add it to the RecipesList which is 
availble for you to view when you click the 'View Recipes' button.
- You can generate my second required event by adding ingredients and instructions to the individuals recipes. This is 
done when you click the 'Write new Recipe' button. It will then walk you through the steps of creating a new recipe, 
adding your chosen amount of elements along the way.
- You can locate my audiovisual component by clicking the 'View Recipes' button, then selecting 'Apple Sauce'. After
 selecting the recipe and having walked through its steps you will be at a step which has an OvenTimer. If you click 
 'Start Timer', after the timer has elapsed, an audible 'Ding' will be heard. There is also a custom chef hat 
 logo on each JFrame.
 - You can save the state of my application automatically when you add a new recipe. After adding a new recipe, it 
 retrieves its most recent state, adds the new recipe and automatically saves the state with the added recipe.
 - You can reload the state of my application by selecting 'View Recipes' which will automatically reload the saved 
 state and present it to you so you can select a recipe to view.
 
 Phase 4: Task 2:
 Test and design a class that is robust.
 Ingredient is my chosen robust class, specifically the methods related to the OvenTimer. If the instruction is asked to
 execute a method that requires an OvenTimer, but that instruction's OvenTimer is null, an exception will be thrown. This
  functionality can be seen tested in InstructionTest by the following test methods:
  - startTimerTestExpectException()
  - displayTimerTestException()
  - displayTimerTestNoException()
  - startTimerTestNoException()
  
  Phase 4: Task 3:
  - An area of tight coupling/poor cohesion was identified between the Instruction class and OvenTimer class. Originally, the 
  instruction class would handle all the operations of playing sound and it would gather the related information of how 
  long to wait and what sound to play, from the OvenTimer class. However, this meant that any change I wanted to make to
   make to the way a sound was played, would also have to be made to the OvenTimer class. It also seemed out of the 
    scope of instruction to actually handle an OvenTimer within itself. To fix this area of poor cohesion, I
    moved everything related to an OvenTimer, including the operations of playing the noise, to the OvenTimer class. The
     Instruction class simply has to make a call to the OvenTimer class and the OvenTimer will do the rest, Instruction 
     class being ignorant of whatever comes next.
 - A second area of poor cohesion was identified in the persistence package. At one time, there was one class which
  handled all of the
 operations related to saving and reading the information saved to the .txt file. However, after analysis, I determined that 
 saving and reading should be seperate classes as one deals with the streaming of information into a file and the other 
 deals with compiling said data from .txt file into readable information. Now, one class is solely responsible for 
 writing and the other reading. This has greatly improved the readability of my code as it is now apparent what each 
 method does due to their class. Contrast to before when it was difficult to understand where the actual saving/reading 
 took place in the code. Making this change also made later bug testing easier as it was apparent which 
 package/operation was failing.