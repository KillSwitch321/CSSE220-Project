# Using GitHub Desktop + Eclipse for Your Final Project

For the final project, your team will use one shared GitHub repository.  
You will use **GitHub Desktop** for Git actions and **Eclipse** for coding.

**Important:** Do not use Eclipse Git tools for this project.  
You do not need a Personal Access Token (PAT) if you are using GitHub Desktop.


This repository is a **starter**. 
It is intentionally minimal and is meant to help you start cleanly and design your own solution.
---

## Starter Code Structure (Read This First)
This starter project uses three main packages:

- **app**: starts the program  
- **model**: stores game objects and game rules  
- **ui**: draws the game and manages the window  

The UI should ask the model what to draw.  
The model should own the game data and rules.




## Step 1: Download this Starter Code

1. Open the starter repository:  
   https://github.com/obscrivn/CSSE220-Final-Project-Starter  
2. Click **Code → Download ZIP**  
3. Move the ZIP file into your local team repository folder  
4. Unzip it inside your team repository folder  

You may rename the unzipped folder to something simple like `FinalProject`.

---
## Step 2: Organize the Project Folder
Recommended structure:

```text
team-repo/
├── .git/
├── FinalProject/
│   ├── src/
│   │   ├── app/
│   │   ├── model/
│   │   └── ui/
│   ├── assets/
│   │   ├── images/
│   │   ├── sounds/        (optional)
│   │   └── levels/        (optional later)
│   ├── .project
│   └── .classpath

```
## Responsibility Overview
Use this structure for your project at least initially. This ensures your design follows good OOP practices. You may extend or refactor this structure later, but your initial design should follow this pattern.

**MainApp**
- creates `GameModel`
- creates `GameWindow`
- connects them once at startup

**GameWindow**
- creates `JFrame`
- adds `GameComponent`
- handles window setup only

**GameModel**
- owns game state and rules

**GameComponent**
- draws the game
- handles user input


**Important:**
- Do NOT leave files in Downloads  
- Do NOT copy into Eclipse workspace  
- Everything must stay inside your **GitHub repo local folder** 
---
## Step 3: Import the Project into Eclipse
1. Open Eclipse  
2. Go to **File → Import**  
3. Select **Existing Projects into Workspace**  
4. Choose the project folder inside your team repository  
5. **Uncheck** “Copy projects into workspace”  
6. Click **Finish**  
7. Run the project to confirm it works  

If nothing appears, try:
- **Projects from Folder or Archive**

Select the folder from inside your repository.

---

## Step 4: Fetch Before You Commit

Before committing, practice the workflow:

1. Open GitHub Desktop  
2. Click **Fetch origin**  
3. If needed, click **Pull origin**

---
## Step 5: Commit and Push
1. In GitHub Desktop, verify your files appear  
2. Add a commit message: Initial Eclipse project setup
3. Click **Commit to main**  
4. Click **Push origin**

---
## Step 6: Check GitHub Online

1. Open your repo on GitHub  
2. Confirm your files are uploaded  
3. Make sure `src` folder is visible 

---
## Step 7: Team Setup

1. Open GitHub Desktop  
2. Click **Fetch origin → Pull origin**  
3. Open Eclipse  
4. Go to **File → Import → Existing Projects into Workspace**  
5. Select the project folder inside the repo  
6. **Uncheck** “Copy projects into workspace”  
7. Click **Finish**  
8. Run the project  

---
# Good Advice for Minimizing Merge Conflicts

* [Pair program whenever possible](https://rose-hulman.hosted.panopto.com/Panopto/Pages/Viewer.aspx?id=ddab27fc-a8a4-4cd0-a8f8-abaf013a3f22)
* Always do a Fetch/Pull before you begin programming
* Always do a Commit when you finish

# Git bash (Command Line)

* A more advanced and full feature program can be used to use [Git for Windows](https://gitforwindows.org/)
* MacOS and Linux have terminal/consoles that can interact with git natively
* There might be times when using these tools will be easier than Eclipse alone
* You are welcome to install it, but in most cases it should not be required
* More about git: [git-handbook](https://guides.github.com/introduction/git-handbook/)

# GitHub Desktop (GUI Application)
* A more intuitive interface than Eclipse and does not require command line familiarity
* Download here [https://desktop.github.com/](https://desktop.github.com/)
