# DateCalculator
Used to calculate the duration of two dates. fancy options such as excluding custom days or weekends and such
 public String CalculateInterval(){    
        if(dayCheckBox.isChecked())
        {
            if(!saturdayCheckbox.isChecked() && !sundayCheckbox.isChecked() && !customdateCheckbox.isChecked()){
                return totalNormalDays() + " Day(s) between " + startCalendar.toString() + " and " + endCalendar.toString();
            }
            if(saturdayCheckbox.isChecked()){
                return totalNormalDays() - findNumberOfSaturdays() + " Day(s) between " + startCalendar.toString() + " and " + endCalendar.toString() + "; excluding Saturdays";
            }
            if(sundayCheckbox.isChecked()) {
                return totalNormalDays() - findNumberOfSunday() + " Day(s) between " + startCalendar.toString() + " and " + endCalendar.toString() + "; excluding Sundays.";
            }
            if(sundayCheckbox.isChecked() && saturdayCheckbox.isChecked()){
                return weekdays() + " Day(s) between " + startCalendar.toString() + " and " + endCalendar.toString() + "; excluding weekends.";
            }
            if(customdateCheckbox.isChecked()){
                if(saturdayCheckbox.isChecked()){
                    return totalNormalDays() - findNumberOfSaturdays() + " Day(s) between " + startCalendar.toString() + " and " + endCalendar.toString() + "; excluding Saturdays and " + customCalendar.toString();
                }
                if(sundayCheckbox.isChecked()) {
                    return totalNormalDays() - findNumberOfSunday() + " Day(s) between " + startCalendar.toString() + " and " + endCalendar.toString() + "; excluding Sundays and " + customCalendar.toString();
                }
                if(sundayCheckbox.isChecked() && saturdayCheckbox.isChecked()){
                    return weekdays() + " Day(s) between " + startCalendar.toString() + " and " + endCalendar.toString() + "; excluding weekends and " + customCalendar.toString();
                }
            }
        }

        if(yearCheckBox.isChecked()){
            if(!saturdayCheckbox.isChecked() && !sundayCheckbox.isChecked() && !customdateCheckbox.isChecked()){
                return totalNormalYears() + " Year(s) between " + startCalendar.toString() + " and " + endCalendar.toString();
            }
            if(saturdayCheckbox.isChecked()){
                return (int)((totalNormalDays() - findNumberOfSaturdays())/365) + " Year(s) between " + startCalendar.toString() + " and " + endCalendar.toString() + "; excluding Saturdays";
            }
            if(sundayCheckbox.isChecked()){
                return (int)((totalNormalDays() - findNumberOfSunday())/365) + " Year(s) between " + startCalendar.toString() + " and " + endCalendar.toString() + "; excluding Sundays";
            }
            if(sundayCheckbox.isChecked() && saturdayCheckbox.isChecked()){
                return (int)(weekdays()/365) + " Year(s) between " + startCalendar.toString() + " and " + endCalendar.toString() + "; excluding weekends.";
            }
            if(customdateCheckbox.isChecked()){
                if(saturdayCheckbox.isChecked()){
                    return (int)((totalNormalDays() - findNumberOfSaturdays())/365) + " Year(s) between " + startCalendar.toString() + " and " + endCalendar.toString() + "; excluding Saturdays and " + customCalendar.toString();
                }
                if(sundayCheckbox.isChecked()) {
                    return (int)((totalNormalDays() - findNumberOfSunday())/365) + " Year(s) between " + startCalendar.toString() + " and " + endCalendar.toString() + "; excluding Sundays and " + customCalendar.toString();
                }
                if(sundayCheckbox.isChecked() && saturdayCheckbox.isChecked()){
                    return (int)(weekdays()/365) + " Year(s) between " + startCalendar.toString() + " and " + endCalendar.toString() + "; excluding weekends and " + customCalendar.toString();
                }
            }
        }
        
        if(dayCheckBox.isChecked() && yearCheckBox.isChecked()){
            if(!saturdayCheckbox.isChecked() && !sundayCheckbox.isChecked() && !customdateCheckbox.isChecked()){
                return totalNormalYears() + " Year(s) " + totalNormalDays()%365 + " Day(s) between " + startCalendar.toString() + " and " + endCalendar.toString();
            }
            if(saturdayCheckbox.isChecked()){
                return (int)((totalNormalDays() - findNumberOfSaturdays())/365) + " Year(s)" + (totalNormalDays() - findNumberOfSaturdays())%365 + " Day(s) between " + startCalendar.toString() + " and " + endCalendar.toString() + "; excluding Saturdays";
            }
            if(sundayCheckbox.isChecked()) {
                return (int)((totalNormalDays() - findNumberOfSunday())/365) + " Year(s) " + (totalNormalDays() - findNumberOfSunday())%365 + " Day(s) between " + startCalendar.toString() + " and " + endCalendar.toString() + "; excluding Sundays.";
            }
            if(sundayCheckbox.isChecked() && saturdayCheckbox.isChecked()){
                return (int)(weekdays()/365) + " Year(s) " + weekdays()%365 + " Day(s) between " + startCalendar.toString() + " and " + endCalendar.toString() + "; excluding weekends.";
            }
            if(customdateCheckbox.isChecked()){
                if(saturdayCheckbox.isChecked()){
                    return (int)((totalNormalDays() - findNumberOfSaturdays())/365) + " Year(s)" + (totalNormalDays() - findNumberOfSaturdays())%365 + " Day(s) between " + startCalendar.toString() + " and " + endCalendar.toString() + "; excluding Saturdays and " + customCalendar.toString();
                }
                if(sundayCheckbox.isChecked()) {
                    return (int)((totalNormalDays() - findNumberOfSunday())/365) + " Year(s) " + (totalNormalDays() - findNumberOfSunday())%365 + " and " + endCalendar.toString() + "; excluding Sundays and " + customCalendar.toString();
                }
                if(sundayCheckbox.isChecked() && saturdayCheckbox.isChecked()){
                    return (int)(weekdays()/365) + " Year(s) " + weekdays()%365 + " Day(s) between " + startCalendar.toString() + " and " + endCalendar.toString() + "; excluding weekends and " + customCalendar.toString();
                }
            }
        }
        return "No criteria matched.";
    }
