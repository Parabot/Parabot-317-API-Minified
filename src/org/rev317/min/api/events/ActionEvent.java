package org.rev317.min.api.events;

public final class ActionEvent{
    private int index,cmd1,cmd2,cmd3,action;
    
    public ActionEvent(int action,int cmd1,int cmd2, int cmd3,int index){
      this.action = action;
      this.cmd1 = cmd1;
      this.cmd2 = cmd2;
      this.cmd3 = cmd3;
      this.index = index;
    }
    
    public int getCmd1(){
      return cmd1;
    }
    
    public int getCmd2(){
      return cmd2;
    }
    
     public int getCmd3(){
      return cmd3;
    }
    
    public int getAction(){
      return action;
    }
    
    public int getIndex(){
        return index;
    }


}
