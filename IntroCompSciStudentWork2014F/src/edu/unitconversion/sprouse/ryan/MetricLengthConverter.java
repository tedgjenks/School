package edu.unitconversion.sprouse.ryan;
import edu.jenks.dist.unitconversion.*;

// compiler error is because the convertible procedures have not been implememted ye
public class MetricLengthConverter implements Convertible
{
    public double convert(double numberStartingUnits, String startingUnit, String desiredUnit)
    {
        return convertFromMeters(convertToMeters(numberStartingUnits, startingUnit), desiredUnit);
    }
    public String convertForDisplay(double numberStartingUnits,String startingUnit,String desiredUnit)                          
    {
        String display = "";
        display += numberStartingUnits;
        display += " ";
        display += startingUnit;
        display += " = ";
        display += convert(numberStartingUnits, startingUnit, desiredUnit);
        display += " ";
        display += desiredUnit;
        display += ".";
        if(desiredUnit == "bm"){
            return "Unit not supported.";
        }
        if(startingUnit == "bm"){
            return "Unit not supported.";
        }else{
            return display;
        }
    }
    public double convertFromMeters(double numberMeters, String unit)
    {
        // convert numberUnits of unit to meters
        if(unit == "nm"){
            // value is given in nanometers
            return numberMeters * 1000000000;
        }
        if(unit == "um"){
            // value is given in micrometers
            return numberMeters * 1000000; 
        }
        if(unit == "mm"){
            // value is given in millimeters
            return numberMeters * 1000;
        }
        if(unit == "cm"){
            // value is given in centimeters
            return numberMeters * 100;
        }
        if(unit == "dm"){
            // value is given in decimeters
            return numberMeters * 10;
        }
        if(unit == "m"){
            return numberMeters;
        }
        if(unit == "dam"){
            // value is given in decameters
            return numberMeters / 10;
        }
        if(unit == "hm"){
            // value is given in hectometers
            return numberMeters / 100;
        }
        if(unit == "km"){
            // value is given in kilometers
            return numberMeters / 1000;
        }
        if(unit == "Mm"){
            // value is given in megameters
            return numberMeters / 1000000;
        }
        if(unit == "Gm"){
            // value is given in gigameters
            return numberMeters / 1000000000;
        }
        
        return 0;
    }
    public double convertToMeters(double numberUnits, String unit)
    {
        // convert numberUnits of unit to meters
        //if(unit == "nm"){
        //    if(unit == "um"){
        //        if(unit == "mm"){
        //            if(unit == "cm"){
        //                if(unit == "dm"){
        //                    if(unit == "m"){
        //                        if(unit == "dam"){
        //                           if(unit == "hm"){
        //                                if(unit == "km"){
        //                                    if(unit == "Mm"){
        //                                        if(unit == "Gm"){
        //                                            
        //                                        }else{
        //                                            return 
        //                                        }
        //                                    }  
        //                                }
        //                            } 
        //                        }
        //                    }
        //                }
        //            }
        //        }
        //    }
        //}
        
        if(unit == "nm"){
            // value is given in nanometers
            return numberUnits / 1000000000;
        }
        if(unit == "um"){
            // value is given in micrometers
            return numberUnits / 1000000; 
        }
        if(unit == "mm"){
            // value is given in millimeters
            return numberUnits / 1000;
        }
        if(unit == "cm"){
            // value is given in centimeters
            return numberUnits / 100;
        }
        if(unit == "dm"){
            // value is given in decimeters
            return numberUnits / 10;
        }
        if(unit == "m"){
            return numberUnits;
        }
        if(unit == "dam"){
            // value is given in decameters
            return numberUnits * 10;
        }
        if(unit == "hm"){
            // value is given in hectometers
            return numberUnits * 100;
        }
        if(unit == "km"){
            // value is given in kilometers
            return numberUnits * 1000;
        }
        if(unit == "Mm"){
            // value is given in megameters
            return numberUnits * 1000000;
        }
        if(unit == "Gm"){
            // value is given in gigameters
            return numberUnits * 1000000000;
        }
        return 0;
    }
}


// public interface Convertible
// nm (nanometer) 
// um (micrometer) 
// mm (millimeter)
// cm (centimeter)
// dm (decimeter)
// dam (decameter)
// hm (hectometer)
// km (kilometer)
// Mm (megameter)
// Gm (gigameter)




















