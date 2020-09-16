package ru.geekbrains;

public class Animal {
    protected String name;
    protected float limRun;
    protected float limSwim;
    protected float limJump;

    public Animal(String name, float limRun, float limSwim, float limJump) {
        this.name = name;
        this.limRun = limRun;
        this.limSwim = limSwim;
        this.limJump = limJump;
    }

    public void run(float dist) {
        if (dist <= limRun) {
            System.out.println(name + " пробежал " + dist);
        } else {
            System.out.println(name + " не смог пробежать " + dist);
        }
    }

    public void swim(float dist) {
        if (dist <= limSwim) {
            System.out.println(name + " проплыл " + dist);
        } else {
            System.out.println(name + " не смог проплыть " + dist);
        }
    }

    public void jump(float height) {
        if (height <= limJump) {
            System.out.println(name + " перепрыгнул высоту " + height);
        } else {
            System.out.println(name + " не смог перепрыгнуть высоту " + height);
        }
    }
}
