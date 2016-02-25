/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sk.tuke.oop.game.actors;

import sk.tuke.oop.game.actors.ripley.Ripley;
import sk.tuke.oop.framework.Actor;
import sk.tuke.oop.framework.Animation;
import sk.tuke.oop.game.commands.Move;

/**
 *
 * @author admin
 */
public class Alien extends AbstractCharacter implements Movable, Enemy{

    int movementInterval;
    double i;
    Ripley ripley;
    Move moveUp;
    Move moveDown;
    Move moveRight;
    Move moveLeft;
    Move moveDownRight;
    Move moveDownLeft;
    Move moveUpRight;
    Move moveUpLeft;    
    
    public Alien()
    {
        normalAnimation = new Animation("resources/sprites/warrior_alien.png",32,32,100);
        normalAnimation.setPingPong(true);
        setAnimation(normalAnimation);
        step = 2;
        movementInterval = 30;
        i = Math.random();
        setHealth(20);
    }
    
    @Override
    public void act() { 
        normalAnimation.stop();
        if(ripley == null)
        {
            for(Actor actor : getWorld())
            {
                if(actor instanceof Ripley)
                {
                    ripley = (Ripley) actor;
                }
            }
        }
        if(moveUp == null)
        {
            moveUp = new Move(this,1,0,-1);
        }
        if(moveDown == null)
        {
            moveDown = new Move(this,1,0,1);
        }
        if(moveRight == null)
        {
            moveRight = new Move(this,1,1,0);
        }
        if(moveLeft == null)
        {
            moveLeft = new Move(this,1,-1,0);
        }
        if(moveDownRight == null)
        {
            moveDownRight = new Move(this,1,1,1);
        }
        if(moveDownLeft == null)
        {
            moveDownLeft = new Move(this,1,-1,1);
        }
        if(moveUpRight == null)
        {
            moveUpRight = new Move(this,1,1,-1);
        }
        if(moveUpLeft == null)
        {
            moveUpLeft = new Move(this,1,-1,-1);
        }
        

        if(movementInterval == 0)
        {
             i = Math.random();
             movementInterval = 30;
        }
            if(i > 0 && i <= 0.06)
            {
                moveUp.Execute();
            } 
            
            if (i > 0.06 && i <= 0.12)
            {
                moveDown.Execute();
                
            } 
            
            if (i > 0.12 && i <= 0.18)
            {
                moveRight.Execute();
                
            } 
            
            if (i > 0.18 && i <= 0.24)
            {
                moveLeft.Execute();
            }
            
            if (i > 0.24 && i <= 0.3)
            {
                moveDownRight.Execute();
            }
            if (i > 0.3 && i <= 0.36)
            {
                moveDownLeft.Execute();
            }   
            if (i > 0.36 && i <= 0.42)
            {
                moveUpRight.Execute();
            }            
            if (i > 0.42 && i <= 0.48)
            {
                moveUpLeft.Execute();
            }            
            if (i > 0.48 && i <= 1)
            {
            }              
            
            
        movementInterval -=1;
        
        if(this.intersects(ripley))
        {
            ripley.setHealth(ripley.getHealth() - 1);
        }
        
        if(getHealth() == 0){
            getWorld().removeActor(this);
            LargeExplosion impact = new LargeExplosion();
            impact.setPosition(getX(), getY());
            impact.getAnimation().setDuration(10);
            impact.setTimer(10);
            getWorld().addActor(impact);
            impact.explode();            
        }
    } 
}

