package com.ly.blog.blogsystem.test;

import java.util.Scanner;

/**
 * Created by BorisLiu on 2019/12/18
 */
class Main{

    public static void main(String[] args){
        Scanner in = new Scanner(System.in);
        String str  = in.nextLine();
        String[] tree = str.split(" ");
        System.out.println(tree);
//        for (int i=0;i<tree.length;i++){
//            System.out.println(tree[i]);
//        }


    }

    public String[] result(String[] tree,int parent){
        if (tree == null){
            return null;
        }
        int left = parent * 2 + 1;
        int right = parent * 2 + 2;

        return null;

    }

}
