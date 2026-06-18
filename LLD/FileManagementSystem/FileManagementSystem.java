
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReentrantReadWriteLock;

// Functional Requirements: 
// - Create a new file or directory
// - Read/Write contents into the files
// - Delete a file/directory
// - Display contents of a directory
// - Get total size of a file or directory

// Non Functional Requirements:
// - Extensible:
// - Thread Safe (Concurrent Reads and Writes)





// Entities: 
// - FileSystemNode (Abstract

// import java.nio.file.FileSystem;

// import java.nio.file.FileSystem;

//  class) 
//     +name
//     - getName()
//     - getSize()
//     - 


// - File
//     - read()
//     - write()
// - directory
//     +Map<String, FileSystemNode>
//     - addNode()
//     - removeNode()
//     - getChildren()

// - FileSystem (Orchestrator)
//     - mkdir()
//     - pwd()
//     - ls()
//     - rm()
//     - touch()
//     - readFile()
//     - writeFile()



abstract class FileSystemNode{
    protected String name;
    protected Directory parent;
    protected LocalDateTime createdAt;
    public FileSystemNode(String name){
        this.name = name;
    }

    public String getName(){
        return name;
    }   

    public void setName(String name){
        this.name = name;
    }   

    public Directory getParent() {
        return parent;
    }

    public void setParent(Directory parent) {
        this.parent = parent;
    }

    public abstract int getSize();
    public abstract void printTree(String indent);
    
}

class File extends FileSystemNode{

    private StringBuilder content;
    private final ReentrantReadWriteLock lock;

    public File(String name){
        super(name);
        content = new StringBuilder();
        lock = new ReentrantReadWriteLock();
    }
    @Override
    public int getSize() {
        return content.length();
    }
    
    public String read(){
        lock.readLock().lock();
        try {
            return content.toString();
        } finally{
            lock.readLock().unlock();
        }
    }

    public void write(String str){
        lock.writeLock().lock();
        try {
            content.append(str);
        } finally{
            lock.writeLock().unlock();
        }
    }

    @Override
    public void printTree(String indent){
        System.out.println(indent + name + " (" + getSize() + " bytes)");
    }   
}

class Directory extends FileSystemNode{

    Map<String, FileSystemNode> children;
    private final ReentrantReadWriteLock lock;

    public Directory(String name){
        super(name);
        children = new HashMap<>();
        lock = new ReentrantReadWriteLock();
    }
    @Override
    public int getSize() {
        lock.readLock().lock();

        try {
            int size = 0;
            for(FileSystemNode child: children.values()){
                size += child.getSize();
            }
            return size;
        } finally{
            lock.readLock().unlock();
        }
       
    }
    @Override
    public void printTree(String indent){
        lock.readLock().lock();
        try {
             System.out.println(indent + name);

            for(FileSystemNode child: children.values()){
                child.printTree(indent + " ");
            }
        } finally {
            lock.readLock().unlock();
        }
    }


}


class FileSystem{
    private final Directory root;
    private FileSystemNode currentWorkingDirectory;

    public FileSystem(){
        root = new Directory("/");
        currentWorkingDirectory = root;
    }


    public void mkdir(String path){

    }
    public String pwd(){

        return "";
    }
    public void renameFile(String path){

    }
    public String ls(){
        return "";
    }

    public void cd(){

    }
    public void rm(String path){

    }
    public void printTree(){
        currentWorkingDirectory.printTree("");
    }

}