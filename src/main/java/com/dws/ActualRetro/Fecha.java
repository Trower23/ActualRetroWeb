
package com.dws.ActualRetro;

public class Fecha {
        private int day;
        private int month;
        private int year;

        public Fecha(int day, int month, int year){
            this.day=day;
            this.month= month;
            this.year= year;
        }

        public String toString(){
            return this.day+"/"+this.month+"/"+this.year;
        }
}

