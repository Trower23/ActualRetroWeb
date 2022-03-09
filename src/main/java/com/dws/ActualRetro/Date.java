
package com.dws.ActualRetro;
import lombok.*;

@Data
@NoArgsConstructor
public class Date {
        private int day;
        private int month;
        private int year;

        public Date(int day, int month, int year){
            this.day=day;
            this.month= month;
            this.year= year;
        }

        public void parseDate(String str, String split){
            String [] aux = str.split(split);
            this.day = Integer.parseInt(aux[0]);
            this.month = Integer.parseInt(aux[1]);
            this.year = Integer.parseInt(aux[2]);
        }
        public String toString(){
            return this.day+"/"+this.month+"/"+this.year;
        }
}

