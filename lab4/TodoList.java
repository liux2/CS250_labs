import java.util.ArrayList;//inserting necessary packages (lines 1-5)
import java.util.Iterator;
import java.util.Scanner;
import java.io.File;
import java.io.IOException;

public class TodoList {

    private ArrayList<TodoItem> todoItems;//makes a list containing todo items
    private static final String TODOFILE = "todo.txt";//declaring and assigning the variable TODOFILE to the todo.txt

    public TodoList() {
        todoItems = new ArrayList<TodoItem>();// declares and assigns todoItems to the todo list
    }

    public void addTodoItem(TodoItem todoItem) {
        todoItems.add(todoItem);//adds the item todoItem to the todoItems list
    }

    public Iterator getTodoItems() {
        return todoItems.iterator();//cycles through all the elements in the todoItems loop
    }

    public void readTodoItemsFromFile() throws IOException {
        Scanner fileScanner = new Scanner(new File(TODOFILE));//declares and assigns the new scanner "fileScanner" to the new file "TODOFILE"
        while(fileScanner.hasNext()) {//makes a while loop if there is at least one or more item(s) to process in fileScanner
            String todoItemLine = fileScanner.nextLine();//assigns the todoItemLine to the next string in the TodoItemFile
            Scanner todoScanner = new Scanner(todoItemLine);//assigns the scanner todScanner to todoItemLine
            todoScanner.useDelimiter(",");//splits it up with a comma
            String priority, category, task;//declares the variables priority, category, and task
            priority = todoScanner.next();// assigns the priority to the next string in the todoScanner
            category = todoScanner.next();//assigns the category to the next string in the todoScanner
            task = todoScanner.next();//assigns task to the nexst string in todoScanner
            TodoItem todoItem = new TodoItem(priority, category, task);
            todoItems.add(todoItem);//adds todoItem to the list todoItems
        }
    }

    public void markTaskAsCompleted(int toMarkId) {
        Iterator iterator = todoItems.iterator();//assigns a new iterator called iterator to ToDoItem
        while(iterator.hasNext()) {
            TodoItem todoItem = (TodoItem)iterator.next();//checks the next value in todoItem
            if(todoItem.getId() == toMarkId) {//Lines 44-45 if the ID in the get ID section of ToDoItem = the ID in toMarkID, then the program moves to the markCompleted section in ToDoItem
                todoItem.markCompleted();
            }
        }
    }

    //find task of given priority
    public Iterator findTasksOfPriority(String requestedPriority) {
        ArrayList<TodoItem> priorityList = new ArrayList<TodoItem>();
        // TODO: Add source code that will find and return all tasks of the requestedPriority
        Iterator iterator = todoItems.iterator();
        while(iterator.hasNext()) {
            TodoItem todoItem = (TodoItem)iterator.next();
            if(todoItem.getPriority().equals(requestedPriority)) {
                priorityList.add(todoItem);
            }
        }

        return priorityList.iterator();
    }
    //find task of a given category
    public Iterator findTasksOfCategory(String requestedCategory) {
        ArrayList<TodoItem> categoryList = new ArrayList<TodoItem>();
        // TODO: Add source code that will find and return all tasks for the requestedCategory
        Iterator iterator = todoItems.iterator();
        while(iterator.hasNext()) {
            TodoItem todoItem = (TodoItem)iterator.next();
            if(todoItem.getCategory().equals(requestedCategory)) {
                categoryList.add(todoItem);
            }
        }

        return categoryList.iterator();
    }

    public String toString() {
        StringBuffer buffer = new StringBuffer();//declares a new StringBuffer called buffer
        Iterator iterator = todoItems.iterator();//assigns a new iterator called iterator to the iterator in ToDoItem
        while(iterator.hasNext()) {//while there is still a value in the variable Iterator
            buffer.append(iterator.next().toString());
            if(iterator.hasNext()) {//if there are still values left in the Iterator called iterator, then the program runs line 67
                buffer.append("\n");
            }
        }
        return buffer.toString();//returns "buffer" to the previous method
    }

}
