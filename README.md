# Home Stock Manager
This is a personal project to create an android app via Android Studio that can be used by a household to manage stock of food items in their home.

## Why have I chosen this project?
I have started this project to learn android app creation and tailored the content of the app to improve my daily life. Information from The Home Of Commons (2024) shows The average person spends £250 on food that ends up in the bin every year. My goal is to create an app that can be used to manage food stock which will reduce unnecessary purchasing and lower my own personal food waste.

## Page Design

There are several pages that are required for this project, which are as follows:

<b>Homepage</b>: This is the landing page to give information regarding the app.  
<b>View Page</b>: View all the items stored in the database with their associated name, quantity and required status. Two buttons will be included per row to increment or decrement the item quantity. Items that are marked as required will be displayed at the top. Addtionally, a search bar will be at the top of the page allowing users to search for specific items. Finally, holding an item will allow for deletion.
<b>Edit Page</b>: This is a page that will allow for edditing of the name or quanity of an item.  
<b>Add Page</b>: A page to add new items to the list. Default is required and 0 quantity. 
<b>Menu Page</b>: This is a week planner for meals to further reduce wasted food by allowing extra planning.  
<b>Menu Edit Page</b>: This will allow for edditing or removing the menu item.

## Menu Removal

To keep the menu planning optimal, a python script was created to clear the name of the menu item at a specific time every day. This has been achieved by using the Task Scheduler program on Windows.

## To do - After functional app created:
- Adding item type to be functional with creation.
- Filter with item types.
- Extend the menu section: Possible ingredients list linking to items already in the database.
