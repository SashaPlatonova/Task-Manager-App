# 🗂️ Task Manager App

A simple Android Task Manager application built with **Kotlin** and **Room Database**.  
This app allows users to **create**, **edit**, **delete**,**show**, and view tasks with due dates,user can filter by title(A-Z)/(Z-A) and dueDate(Cloest)/(Overdue) ,status, and encrypted storage for sensitive data. 

---

## 🚀 Features

✅ **Add Tasks** – Create new tasks with title, description, due date, and completion status  
✅ **Edit Tasks** – Update existing tasks easily with pre-filled data  
✅ **Delete Tasks** – Remove tasks with a confirmation dialog  
✅ **Room Database** – All tasks are stored locally with Room   
✅ **Mark as Completed** – Toggle task status between Pending, Completed, and Expired  
✅ **Search Tasks** – Quickly search tasks by title  
✅ *Sort Tasks** – Sort tasks by Title (A-Z / Z-A) or Due Date (Closest / Overdue)  
✅ **Encrypted Storage** – Task title and description are encrypted using AES  
✅ **Task Details Screen** – View task details in a dedicated screen  
✅ Simple and modern user interface  
✅ **Status Bar** – Each task shows a colored status bar:  
  - **Pending** – Task is due but not yet completed  
  - **Completed** – Task is completed  
  - **Expired** – Task due date has passed   

---

## 🧱 Tech Stack

| Component          | Technology Used                   |
|-------------------|------------------------------------|
| Language           | Kotlin                            |
| Database           | Room (Local Storage)              |
| UI                 | RecyclerView, Material Design     |
| Architecture       | MVVM (basic structure)            |
| Async Operations   | Coroutines + LiveData             |
| Encryption         | AES (Advanced Encryption Standard)|
| Adapter/Binding    | ViewBinding, RecyclerView Adapter |
| Android Version    | API 24+ (Android 7.0 and above)   |


---

## 📲 Screens Included

1. **MainActivity** – Displays all saved tasks in a RecyclerView with search, sort, and status indicators (Pending, Completed, Expired). Users can edit or delete tasks from     this screen.  
2. **Add_Task Activity** – Add a new task or edit an existing one with title, description, due date (via DatePicker), and completion status. Validates inputs before saving. 
3. **Show_Task_Data Activity** – View full task details including title, description, due date, and status with a colored status bar.  
4. **TaskAdapter** – RecyclerView Adapter handling UI binding, click listeners for edit/delete, status toggle, and launching the detail view.


---

## 🗃️ Database Design

**Entity:** `Task_Data`  

| Field        | Type           | Description                        |
|--------------|----------------|------------------------------------|
| id           | Int (Auto-generated) | Primary key                        |
| title        | String         | Task title (encrypted in DB)       |
| description  | String         | Task details (encrypted in DB)     |
| dueDate      | String         | Due date stored as timestamp (millis) |
| isCompleted  | Boolean        | Task completion status             |


---

## 📦 How to Run

1. Clone this repository:
   ```bash
   git clone https://github.com/ar-sarkar-77/Task-Manager-App.git

---

## 🖼️ Screenshots 

| Task List | Add/Edit Task | Show Task |
|:--:|:--:|:--:|
| <img width="300" height="2992" alt="Screenshot_20251114_012711" src="https://github.com/user-attachments/assets/31955188-e1f2-4e5a-b6e4-57726e065e92" />|<img width="300" height="2992" alt="Screenshot_20251114_013831" src="https://github.com/user-attachments/assets/3e98723f-22fb-4b19-8f47-71860ddfc42b" />| <img width="300" height="2992" alt="Screenshot_20251114_013259" src="https://github.com/user-attachments/assets/a722b0e3-d177-4115-a04c-7566273be904" />|
---

## 👨‍💻 Author
---

## 👤 Mohammad Anondo Sarkar  
💼 Android App Developer | UI UX Designer | 💻 Computer Science Student  
📧 Email: anondosarkarar77@gmail.com  
🌍 From: Lalmonirhat, Bangladesh  
🌐 Website: https://arsarkar77.blogspot.com  
💬 Built with ❤️, Kotlin, and endless cups of coffee ☕  
