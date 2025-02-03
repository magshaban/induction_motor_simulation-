/*
 * induction_motor_vibration.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Feb 3 2025, 14:26 by COMSOL 6.2.0.339. */
public class induction_motor_vibration {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model.modelPath("C:\\Users\\scima\\Downloads\\Engineering\\Kevvvviiiin");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 2);

    model.component("comp1").mesh().create("mesh1");

    model.component("comp1").physics().create("rmm", "RotatingMachineryMagnetic", "geom1");

    model.study().create("std1");
    model.study("std1").create("time", "Transient");
    model.study("std1").feature("time").setSolveFor("/physics/rmm", true);

//    To import content from file, use:
//    model.param().loadFile("FILENAME");
    model.param().set("f0", "60[Hz]", "Supply frequency");
    model.param().set("w0", "2*pi*f0", "Supply angular frequency");
    model.param().set("n0", "2045", "Number of turns");
    model.param().set("win_angle", "45[deg]", "Phase span of a winding");
    model.param().set("L", "0.08[m]", "Rotor length");
    model.param().set("dy", "0.6[mm]", "Radial misalignment");
    model.param().set("kb", "1e6[N/m]", "Bearing translational stiffness");
    model.param().set("kbr", "1e4[N*m/rad]", "Bearing rotational stiffness");
    model.param().set("kf", "1e6[N/m]", "Foundation translational stiffness");
    model.param().set("kfr", "1e4[N*m/rad]", "Foundation rotational stiffness");

    model.component().create("comp2", true);

    model.component("comp2").geom().create("geom2", 3);

    model.component("comp2").mesh().create("mesh2");

    model.component("comp2").geom("geom2").create("imp1", "Import");
    model.component("comp2").geom("geom2").feature("imp1").set("filename", "induction_motor_vibration.mphbin");
    model.component("comp2").geom("geom2").run("imp1");
    model.component("comp2").geom("geom2").create("wp1", "WorkPlane");
    model.component("comp2").geom("geom2").feature("wp1").set("unite", true);
    model.component("comp2").geom("geom2").feature("wp1").set("quickplane", "yz");
    model.component("comp2").geom("geom2").feature("fin").set("action", "assembly");
    model.component("comp2").geom("geom2").feature("fin").set("createpairs", false);
    model.component("comp2").geom("geom2").run("fin");
    model.component("comp1").geom("geom1").create("cro1", "CrossSection");
    model.component("comp1").geom("geom1").feature("fin").set("action", "assembly");
    model.component("comp1").geom("geom1").run("fin");

    model.component("comp1").variable().create("var1");
    model.component("comp1").variable("var1").set("Ia", "1[A]*sqrt(2)*cos(w0*t)");
    model.component("comp1").variable("var1").descr("Ia", "Current on phase A");
    model.component("comp1").variable("var1").set("Ib", "1[A]*sqrt(2)*cos(w0*t+120[deg])");
    model.component("comp1").variable("var1").descr("Ib", "Current on phase B");
    model.component("comp1").variable("var1").set("Ic", "1[A]*sqrt(2)*cos(w0*t-120[deg])");
    model.component("comp1").variable("var1").descr("Ic", "Current on phase C");

    model.component("comp1").material().create("mat1", "Common");
    model.component("comp1").material("mat1").propertyGroup("def").func().create("eta", "Piecewise");
    model.component("comp1").material("mat1").propertyGroup("def").func().create("Cp", "Piecewise");
    model.component("comp1").material("mat1").propertyGroup("def").func().create("rho", "Analytic");
    model.component("comp1").material("mat1").propertyGroup("def").func().create("k", "Piecewise");
    model.component("comp1").material("mat1").propertyGroup("def").func().create("cs", "Analytic");
    model.component("comp1").material("mat1").propertyGroup("def").func().create("an1", "Analytic");
    model.component("comp1").material("mat1").propertyGroup("def").func().create("an2", "Analytic");
    model.component("comp1").material("mat1").propertyGroup().create("RefractiveIndex", "Refractive index");
    model.component("comp1").material("mat1").propertyGroup().create("NonlinearModel", "Nonlinear model");
    model.component("comp1").material("mat1").propertyGroup().create("idealGas", "Ideal gas");
    model.component("comp1").material("mat1").propertyGroup("idealGas").func().create("Cp", "Piecewise");
    model.component("comp1").material("mat1").label("Air");
    model.component("comp1").material("mat1").set("family", "air");
    model.component("comp1").material("mat1").propertyGroup("def").func("eta").set("arg", "T");
    model.component("comp1").material("mat1").propertyGroup("def").func("eta")
         .set("pieces", new String[][]{{"200.0", "1600.0", "-8.38278E-7+8.35717342E-8*T^1-7.69429583E-11*T^2+4.6437266E-14*T^3-1.06585607E-17*T^4"}});
    model.component("comp1").material("mat1").propertyGroup("def").func("eta").set("argunit", "K");
    model.component("comp1").material("mat1").propertyGroup("def").func("eta").set("fununit", "Pa*s");
    model.component("comp1").material("mat1").propertyGroup("def").func("Cp").set("arg", "T");
    model.component("comp1").material("mat1").propertyGroup("def").func("Cp")
         .set("pieces", new String[][]{{"200.0", "1600.0", "1047.63657-0.372589265*T^1+9.45304214E-4*T^2-6.02409443E-7*T^3+1.2858961E-10*T^4"}});
    model.component("comp1").material("mat1").propertyGroup("def").func("Cp").set("argunit", "K");
    model.component("comp1").material("mat1").propertyGroup("def").func("Cp").set("fununit", "J/(kg*K)");
    model.component("comp1").material("mat1").propertyGroup("def").func("rho")
         .set("expr", "pA*0.02897/R_const[K*mol/J]/T");
    model.component("comp1").material("mat1").propertyGroup("def").func("rho").set("args", new String[]{"pA", "T"});
    model.component("comp1").material("mat1").propertyGroup("def").func("rho").set("fununit", "kg/m^3");
    model.component("comp1").material("mat1").propertyGroup("def").func("rho")
         .set("argunit", new String[]{"Pa", "K"});
    model.component("comp1").material("mat1").propertyGroup("def").func("rho")
         .set("plotargs", new String[][]{{"pA", "101325", "101325"}, {"T", "273.15", "293.15"}});
    model.component("comp1").material("mat1").propertyGroup("def").func("k").set("arg", "T");
    model.component("comp1").material("mat1").propertyGroup("def").func("k")
         .set("pieces", new String[][]{{"200.0", "1600.0", "-0.00227583562+1.15480022E-4*T^1-7.90252856E-8*T^2+4.11702505E-11*T^3-7.43864331E-15*T^4"}});
    model.component("comp1").material("mat1").propertyGroup("def").func("k").set("argunit", "K");
    model.component("comp1").material("mat1").propertyGroup("def").func("k").set("fununit", "W/(m*K)");
    model.component("comp1").material("mat1").propertyGroup("def").func("cs")
         .set("expr", "sqrt(1.4*R_const[K*mol/J]/0.02897*T)");
    model.component("comp1").material("mat1").propertyGroup("def").func("cs").set("args", new String[]{"T"});
    model.component("comp1").material("mat1").propertyGroup("def").func("cs").set("fununit", "m/s");
    model.component("comp1").material("mat1").propertyGroup("def").func("cs").set("argunit", new String[]{"K"});
    model.component("comp1").material("mat1").propertyGroup("def").func("cs")
         .set("plotargs", new String[][]{{"T", "273.15", "373.15"}});
    model.component("comp1").material("mat1").propertyGroup("def").func("an1").set("funcname", "alpha_p");
    model.component("comp1").material("mat1").propertyGroup("def").func("an1")
         .set("expr", "-1/rho(pA,T)*d(rho(pA,T),T)");
    model.component("comp1").material("mat1").propertyGroup("def").func("an1").set("args", new String[]{"pA", "T"});
    model.component("comp1").material("mat1").propertyGroup("def").func("an1").set("fununit", "1/K");
    model.component("comp1").material("mat1").propertyGroup("def").func("an1")
         .set("argunit", new String[]{"Pa", "K"});
    model.component("comp1").material("mat1").propertyGroup("def").func("an1")
         .set("plotargs", new String[][]{{"pA", "101325", "101325"}, {"T", "273.15", "373.15"}});
    model.component("comp1").material("mat1").propertyGroup("def").func("an2").set("funcname", "muB");
    model.component("comp1").material("mat1").propertyGroup("def").func("an2").set("expr", "0.6*eta(T)");
    model.component("comp1").material("mat1").propertyGroup("def").func("an2").set("args", new String[]{"T"});
    model.component("comp1").material("mat1").propertyGroup("def").func("an2").set("fununit", "Pa*s");
    model.component("comp1").material("mat1").propertyGroup("def").func("an2").set("argunit", new String[]{"K"});
    model.component("comp1").material("mat1").propertyGroup("def").func("an2")
         .set("plotargs", new String[][]{{"T", "200", "1600"}});
    model.component("comp1").material("mat1").propertyGroup("def").set("thermalexpansioncoefficient", "");
    model.component("comp1").material("mat1").propertyGroup("def").set("molarmass", "");
    model.component("comp1").material("mat1").propertyGroup("def").set("bulkviscosity", "");
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("thermalexpansioncoefficient", new String[]{"alpha_p(pA,T)", "0", "0", "0", "alpha_p(pA,T)", "0", "0", "0", "alpha_p(pA,T)"});
    model.component("comp1").material("mat1").propertyGroup("def").set("molarmass", "0.02897[kg/mol]");
    model.component("comp1").material("mat1").propertyGroup("def").set("bulkviscosity", "muB(T)");
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("relpermeability", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("relpermittivity", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.component("comp1").material("mat1").propertyGroup("def").set("dynamicviscosity", "eta(T)");
    model.component("comp1").material("mat1").propertyGroup("def").set("ratioofspecificheat", "1.4");
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("electricconductivity", new String[]{"0[S/m]", "0", "0", "0", "0[S/m]", "0", "0", "0", "0[S/m]"});
    model.component("comp1").material("mat1").propertyGroup("def").set("heatcapacity", "Cp(T)");
    model.component("comp1").material("mat1").propertyGroup("def").set("density", "rho(pA,T)");
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("thermalconductivity", new String[]{"k(T)", "0", "0", "0", "k(T)", "0", "0", "0", "k(T)"});
    model.component("comp1").material("mat1").propertyGroup("def").set("soundspeed", "cs(T)");
    model.component("comp1").material("mat1").propertyGroup("def").addInput("temperature");
    model.component("comp1").material("mat1").propertyGroup("def").addInput("pressure");
    model.component("comp1").material("mat1").propertyGroup("RefractiveIndex")
         .set("n", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.component("comp1").material("mat1").propertyGroup("NonlinearModel").set("BA", "(def.gamma+1)/2");
    model.component("comp1").material("mat1").propertyGroup("idealGas").func("Cp").label("Piecewise 2");
    model.component("comp1").material("mat1").propertyGroup("idealGas").func("Cp").set("arg", "T");
    model.component("comp1").material("mat1").propertyGroup("idealGas").func("Cp")
         .set("pieces", new String[][]{{"200.0", "1600.0", "1047.63657-0.372589265*T^1+9.45304214E-4*T^2-6.02409443E-7*T^3+1.2858961E-10*T^4"}});
    model.component("comp1").material("mat1").propertyGroup("idealGas").func("Cp").set("argunit", "K");
    model.component("comp1").material("mat1").propertyGroup("idealGas").func("Cp").set("fununit", "J/(kg*K)");
    model.component("comp1").material("mat1").propertyGroup("idealGas").set("Rs", "R_const/Mn");
    model.component("comp1").material("mat1").propertyGroup("idealGas").set("heatcapacity", "Cp(T)");
    model.component("comp1").material("mat1").propertyGroup("idealGas").set("ratioofspecificheat", "1.4");
    model.component("comp1").material("mat1").propertyGroup("idealGas").set("molarmass", "0.02897");
    model.component("comp1").material("mat1").propertyGroup("idealGas").addInput("temperature");
    model.component("comp1").material("mat1").propertyGroup("idealGas").addInput("pressure");
    model.component("comp1").material("mat1").materialType("nonSolid");
    model.component("comp1").material("mat1").set("family", "air");
    model.component("comp1").material().create("mat2", "Common");
    model.component("comp1").material("mat2").propertyGroup().create("Enu", "Young's modulus and Poisson's ratio");
    model.component("comp1").material("mat2").propertyGroup().create("Murnaghan", "Murnaghan");
    model.component("comp1").material("mat2").label("Aluminum");
    model.component("comp1").material("mat2").set("family", "aluminum");
    model.component("comp1").material("mat2").propertyGroup("def")
         .set("relpermeability", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.component("comp1").material("mat2").propertyGroup("def").set("heatcapacity", "900[J/(kg*K)]");
    model.component("comp1").material("mat2").propertyGroup("def")
         .set("thermalconductivity", new String[]{"238[W/(m*K)]", "0", "0", "0", "238[W/(m*K)]", "0", "0", "0", "238[W/(m*K)]"});
    model.component("comp1").material("mat2").propertyGroup("def")
         .set("electricconductivity", new String[]{"3.774e7[S/m]", "0", "0", "0", "3.774e7[S/m]", "0", "0", "0", "3.774e7[S/m]"});
    model.component("comp1").material("mat2").propertyGroup("def")
         .set("relpermittivity", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.component("comp1").material("mat2").propertyGroup("def")
         .set("thermalexpansioncoefficient", new String[]{"23e-6[1/K]", "0", "0", "0", "23e-6[1/K]", "0", "0", "0", "23e-6[1/K]"});
    model.component("comp1").material("mat2").propertyGroup("def").set("density", "2700[kg/m^3]");
    model.component("comp1").material("mat2").propertyGroup("Enu").set("E", "70[GPa]");
    model.component("comp1").material("mat2").propertyGroup("Enu").set("nu", "0.33");
    model.component("comp1").material("mat2").propertyGroup("Murnaghan").set("l", "-250[GPa]");
    model.component("comp1").material("mat2").propertyGroup("Murnaghan").set("m", "-330[GPa]");
    model.component("comp1").material("mat2").propertyGroup("Murnaghan").set("n", "-350[GPa]");
    model.component("comp1").material("mat2").set("family", "aluminum");
    model.component("comp1").material("mat2").selection().set(18);
    model.component("comp1").material().create("mat3", "Common");
    model.component("comp1").material("mat3").label("Steel: Rotor");
    model.component("comp1").material("mat3").selection().set(19, 20);
    model.component("comp1").material("mat3").propertyGroup("def").set("relpermeability", new String[]{"30"});
    model.component("comp1").material("mat3").propertyGroup("def")
         .set("electricconductivity", new String[]{"1.6e6[S/m]"});
    model.component("comp1").material("mat3").propertyGroup("def").set("relpermittivity", new String[]{"1"});
    model.component("comp1").material().duplicate("mat4", "mat3");
    model.component("comp1").material("mat4").label("Steel: Stator");
    model.component("comp1").material("mat4").selection().set(15);
    model.component("comp1").material("mat4").propertyGroup("def").set("relpermeability", new String[]{"30"});
    model.component("comp1").material("mat4").propertyGroup("def")
         .set("electricconductivity", new String[]{"0[S/m]"});
    model.component("comp1").material("mat4").propertyGroup("def").set("relpermittivity", new String[]{"1"});

    model.component("comp1").physics("rmm").selection()
         .set(3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20);
    model.component("comp1").physics("rmm").prop("d").set("d", "L");
    model.component("comp1").physics("rmm").prop("ShapeProperty").set("order_magneticvectorpotential", 1);
    model.component("comp1").physics("rmm").create("coil1", "Coil", 2);
    model.component("comp1").physics("rmm").feature("coil1").label("Coil: Phase A");
    model.component("comp1").physics("rmm").feature("coil1").selection().set(4, 13);
    model.component("comp1").physics("rmm").feature("coil1").set("ConductorModel", "Multi");
    model.component("comp1").physics("rmm").feature("coil1").set("coilGroup", true);
    model.component("comp1").physics("rmm").feature("coil1").set("ICoil", "Ia");
    model.component("comp1").physics("rmm").feature("coil1").set("N", "n0");
    model.component("comp1").physics("rmm").feature("coil1").create("rcd1", "ReverseCoilGroupDomain", 2);
    model.component("comp1").physics("rmm").feature("coil1").feature("rcd1").selection().set(13);
    model.component("comp1").physics("rmm").feature().duplicate("coil2", "coil1");
    model.component("comp1").physics("rmm").feature("coil2").label("Coil: Phase B");
    model.component("comp1").physics("rmm").feature("coil2").selection().set(7, 9);
    model.component("comp1").physics("rmm").feature("coil2").set("ICoil", "Ib");
    model.component("comp1").physics("rmm").feature("coil2").feature("rcd1").selection().set(7);
    model.component("comp1").physics("rmm").feature().duplicate("coil3", "coil2");
    model.component("comp1").physics("rmm").feature("coil3").label("Coil: Phase C");
    model.component("comp1").physics("rmm").feature("coil3").selection().set(5, 11);
    model.component("comp1").physics("rmm").feature("coil3").set("ICoil", "Ic");
    model.component("comp1").physics("rmm").feature("coil3").feature("rcd1").selection().set(5);
    model.component("comp1").physics("rmm").create("cont1", "Continuity", 1);
    model.component("comp1").physics("rmm").feature("cont1").set("pairs", new String[]{"ap1"});
    model.component("comp1").physics("rmm").create("fcal1", "ForceCalculation", 2);
    model.component("comp1").physics("rmm").feature("fcal1").label("Force Calculation: Rotor");
    model.component("comp1").physics("rmm").feature("fcal1").set("ForceName", "Rotor");
    model.component("comp1").physics("rmm").feature("fcal1")
         .set("TorqueRotationPoint", new String[]{"0", "dy", "0"});
    model.component("comp1").physics("rmm").feature("fcal1").selection().set(18, 19, 20);
    model.component("comp1").physics("rmm").feature().duplicate("fcal2", "fcal1");
    model.component("comp1").physics("rmm").feature("fcal2").label("Force Calculation: Stator");
    model.component("comp1").physics("rmm").feature("fcal2").set("ForceName", "Stator");
    model.component("comp1").physics("rmm").feature("fcal2").set("TorqueRotationPoint", new int[]{0, 0, 0});
    model.component("comp1").physics("rmm").feature("fcal2").selection()
         .set(3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15);

    model.component("comp1").mesh("mesh1").automatic(false);
    model.component("comp1").mesh("mesh1").feature("size").set("hauto", 8);
    model.component("comp1").mesh("mesh1").feature("ftri1").create("size1", "Size");
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size1").selection().set(18, 19, 20);
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size1").set("hauto", 3);
    model.component("comp1").mesh("mesh1").feature("ftri1").feature().duplicate("size2", "size1");
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size2").selection().set(17);
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size2").set("custom", true);
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size2").set("hmaxactive", true);
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size2").set("hmax", "0.00075");
    model.component("comp1").mesh("mesh1").feature("ftri1").feature().duplicate("size3", "size2");
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size3").selection().set(16);
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size3").set("hmax", 0.00125);
    model.component("comp1").mesh("mesh1").create("bl1", "BndLayer");
    model.component("comp1").mesh("mesh1").feature("bl1").create("blp", "BndLayerProp");
    model.component("comp1").mesh("mesh1").feature("bl1").selection().geom(2);
    model.component("comp1").mesh("mesh1").feature("bl1").selection().set();
    model.component("comp1").mesh("mesh1").feature("bl1").selection().allGeom();
    model.component("comp1").mesh("mesh1").feature("bl1").selection().geom("geom1", 2);
    model.component("comp1").mesh("mesh1").feature("bl1").selection().set(19);
    model.component("comp1").mesh("mesh1").feature("bl1").feature("blp").selection().set(163, 164);
    model.component("comp1").mesh("mesh1").create("bl2", "BndLayer");
    model.component("comp1").mesh("mesh1").feature("bl2").create("blp", "BndLayerProp");
    model.component("comp1").mesh("mesh1").feature("bl2").selection().geom("geom1", 2);
    model.component("comp1").mesh("mesh1").feature("bl2").selection().set(18);
    model.component("comp1").mesh("mesh1").feature("bl2").feature("blp").selection().set(161, 162);

    model.component("comp2").physics().create("mbd", "MultibodyDynamics", "geom2");

    model.study("std1").feature("time").setSolveFor("/physics/mbd", true);

    model.component("comp2").view("view2").hideEntities().create("hide1");
    model.component("comp2").view("view2").hideEntities("hide1").geom(3);
    model.component("comp2").view("view2").hideEntities("hide1").add(14);

    model.component("comp2").physics("mbd").selection()
         .set(1, 2, 3, 4, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 17, 18, 21, 23, 25, 27, 29, 30, 31, 32, 33, 34, 35, 36);

    model.component("comp2").material().create("mat5", "Common");
    model.component("comp2").material("mat5").propertyGroup().create("Enu", "Young's modulus and Poisson's ratio");
    model.component("comp2").material("mat5").propertyGroup("Enu").func().create("an1", "Analytic");
    model.component("comp2").material("mat5").propertyGroup("Enu").func().create("an2", "Analytic");
    model.component("comp2").material("mat5").propertyGroup().create("Murnaghan", "Murnaghan");
    model.component("comp2").material("mat5").propertyGroup()
         .create("ElastoplasticModel", "Elastoplastic material model");
    model.component("comp2").material("mat5").propertyGroup("ElastoplasticModel").func()
         .create("int1", "Interpolation");
    model.component("comp2").material("mat5").propertyGroup().create("Ludwik", "Ludwik");
    model.component("comp2").material("mat5").propertyGroup("Ludwik").func().create("int1", "Interpolation");
    model.component("comp2").material("mat5").propertyGroup().create("JohnsonCook", "Johnson-Cook");
    model.component("comp2").material("mat5").propertyGroup().create("Swift", "Swift");
    model.component("comp2").material("mat5").propertyGroup().create("Voce", "Voce");
    model.component("comp2").material("mat5").propertyGroup("Voce").func().create("int1", "Interpolation");
    model.component("comp2").material("mat5").propertyGroup().create("HockettSherby", "Hockett-Sherby");
    model.component("comp2").material("mat5").propertyGroup("HockettSherby").func().create("int1", "Interpolation");
    model.component("comp2").material("mat5").propertyGroup().create("ArmstrongFrederick", "Armstrong-Frederick");
    model.component("comp2").material("mat5").propertyGroup("ArmstrongFrederick").func()
         .create("int1", "Interpolation");
    model.component("comp2").material("mat5").propertyGroup().create("Norton", "Norton");
    model.component("comp2").material("mat5").propertyGroup().create("Garofalo", "Garofalo (hyperbolic sine)");
    model.component("comp2").material("mat5").propertyGroup()
         .create("ChabocheViscoplasticity", "Chaboche viscoplasticity");
    model.component("comp2").material("mat5").label("Structural steel");
    model.component("comp2").material("mat5").set("family", "custom");
    model.component("comp2").material("mat5")
         .set("customspecular", new double[]{0.7843137254901961, 0.7843137254901961, 0.7843137254901961});
    model.component("comp2").material("mat5")
         .set("customdiffuse", new double[]{0.6666666666666666, 0.6666666666666666, 0.6666666666666666});
    model.component("comp2").material("mat5")
         .set("customambient", new double[]{0.6666666666666666, 0.6666666666666666, 0.6666666666666666});
    model.component("comp2").material("mat5").set("noise", true);
    model.component("comp2").material("mat5").set("fresnel", 0.9);
    model.component("comp2").material("mat5").set("roughness", 0.3);
    model.component("comp2").material("mat5").set("metallic", 0);
    model.component("comp2").material("mat5").set("pearl", 0);
    model.component("comp2").material("mat5").set("diffusewrap", 0);
    model.component("comp2").material("mat5").set("clearcoat", 0);
    model.component("comp2").material("mat5").set("reflectance", 0);
    model.component("comp2").material("mat5").propertyGroup("def").set("lossfactor", "0.02");
    model.component("comp2").material("mat5").propertyGroup("def")
         .set("relpermeability", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.component("comp2").material("mat5").propertyGroup("def").set("heatcapacity", "475[J/(kg*K)]");
    model.component("comp2").material("mat5").propertyGroup("def")
         .set("thermalconductivity", new String[]{"44.5[W/(m*K)]", "0", "0", "0", "44.5[W/(m*K)]", "0", "0", "0", "44.5[W/(m*K)]"});
    model.component("comp2").material("mat5").propertyGroup("def")
         .set("electricconductivity", new String[]{"4.032e6[S/m]", "0", "0", "0", "4.032e6[S/m]", "0", "0", "0", "4.032e6[S/m]"});
    model.component("comp2").material("mat5").propertyGroup("def")
         .set("relpermittivity", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.component("comp2").material("mat5").propertyGroup("def")
         .set("thermalexpansioncoefficient", new String[]{"12.3e-6[1/K]", "0", "0", "0", "12.3e-6[1/K]", "0", "0", "0", "12.3e-6[1/K]"});
    model.component("comp2").material("mat5").propertyGroup("def").set("density", "7850[kg/m^3]");
    model.component("comp2").material("mat5").propertyGroup("Enu").func("an1").set("funcname", "E");
    model.component("comp2").material("mat5").propertyGroup("Enu").func("an1")
         .set("expr", "200[GPa]*(1 - 3.34e-4[1/K]*(T - 293.15[K]))");
    model.component("comp2").material("mat5").propertyGroup("Enu").func("an1").set("args", new String[]{"T"});
    model.component("comp2").material("mat5").propertyGroup("Enu").func("an1").set("fununit", "Pa");
    model.component("comp2").material("mat5").propertyGroup("Enu").func("an1").set("argunit", new String[]{"K"});
    model.component("comp2").material("mat5").propertyGroup("Enu").func("an1")
         .set("plotargs", new String[][]{{"T", "0", "1500"}});
    model.component("comp2").material("mat5").propertyGroup("Enu").func("an2").set("funcname", "nu");
    model.component("comp2").material("mat5").propertyGroup("Enu").func("an2")
         .set("expr", "0.3*(1 + 1e-4[1/K]*(T - 293.15[K]))");
    model.component("comp2").material("mat5").propertyGroup("Enu").func("an2").set("args", new String[]{"T"});
    model.component("comp2").material("mat5").propertyGroup("Enu").func("an2").set("fununit", "1");
    model.component("comp2").material("mat5").propertyGroup("Enu").func("an2").set("argunit", new String[]{"K"});
    model.component("comp2").material("mat5").propertyGroup("Enu").func("an2")
         .set("plotargs", new String[][]{{"T", "0", "1500"}});
    model.component("comp2").material("mat5").propertyGroup("Enu").set("E", "E(T)");
    model.component("comp2").material("mat5").propertyGroup("Enu").set("nu", "nu(T)");
    model.component("comp2").material("mat5").propertyGroup("Enu").addInput("temperature");
    model.component("comp2").material("mat5").propertyGroup("Murnaghan").set("l", "-3.0e11[Pa]");
    model.component("comp2").material("mat5").propertyGroup("Murnaghan").set("m", "-6.2e11[Pa]");
    model.component("comp2").material("mat5").propertyGroup("Murnaghan").set("n", "-7.2e11[Pa]");
    model.component("comp2").material("mat5").propertyGroup("ElastoplasticModel").func("int1").set("funcname", "a");
    model.component("comp2").material("mat5").propertyGroup("ElastoplasticModel").func("int1")
         .set("table", new String[][]{{"600[K]", "1"}, {"1100[K]", "0.1"}, {"1643[K]", "0"}});
    model.component("comp2").material("mat5").propertyGroup("ElastoplasticModel").func("int1")
         .set("fununit", new String[]{"1"});
    model.component("comp2").material("mat5").propertyGroup("ElastoplasticModel").func("int1")
         .set("argunit", new String[]{"K"});
    model.component("comp2").material("mat5").propertyGroup("ElastoplasticModel").set("sigmags", "350[MPa]*a(T)");
    model.component("comp2").material("mat5").propertyGroup("ElastoplasticModel").set("Et", "1.045[GPa]*a(T)");
    model.component("comp2").material("mat5").propertyGroup("ElastoplasticModel").set("Ek", "1.045[GPa]*a(T)");
    model.component("comp2").material("mat5").propertyGroup("ElastoplasticModel")
         .set("sigmagh", "1.050[GPa]*epe*a(T)");
    model.component("comp2").material("mat5").propertyGroup("ElastoplasticModel").addInput("temperature");
    model.component("comp2").material("mat5").propertyGroup("ElastoplasticModel").addInput("effectiveplasticstrain");
    model.component("comp2").material("mat5").propertyGroup("Ludwik").func("int1").set("funcname", "a");
    model.component("comp2").material("mat5").propertyGroup("Ludwik").func("int1")
         .set("table", new String[][]{{"600[K]", "1"}, {"1100[K]", "0.1"}, {"1643[K]", "0"}});
    model.component("comp2").material("mat5").propertyGroup("Ludwik").func("int1").set("fununit", new String[]{"1"});
    model.component("comp2").material("mat5").propertyGroup("Ludwik").func("int1").set("argunit", new String[]{"K"});
    model.component("comp2").material("mat5").propertyGroup("Ludwik").set("k_lud", "560[MPa]*a(T)");
    model.component("comp2").material("mat5").propertyGroup("Ludwik").set("n_lud", "0.61");
    model.component("comp2").material("mat5").propertyGroup("Ludwik").addInput("temperature");
    model.component("comp2").material("mat5").propertyGroup("JohnsonCook").set("k_jcook", "560[MPa]");
    model.component("comp2").material("mat5").propertyGroup("JohnsonCook").set("n_jcook", "0.61");
    model.component("comp2").material("mat5").propertyGroup("JohnsonCook").set("C_jcook", "0.12");
    model.component("comp2").material("mat5").propertyGroup("JohnsonCook").set("epet0_jcook", "1[1/s]");
    model.component("comp2").material("mat5").propertyGroup("JohnsonCook").set("m_jcook", "0.6");
    model.component("comp2").material("mat5").propertyGroup("Swift").set("e0_swi", "0.021");
    model.component("comp2").material("mat5").propertyGroup("Swift").set("n_swi", "0.2");
    model.component("comp2").material("mat5").propertyGroup("Voce").func("int1").set("funcname", "a");
    model.component("comp2").material("mat5").propertyGroup("Voce").func("int1")
         .set("table", new String[][]{{"600[K]", "1"}, {"1100[K]", "0.1"}, {"1643[K]", "0"}});
    model.component("comp2").material("mat5").propertyGroup("Voce").func("int1").set("fununit", new String[]{"1"});
    model.component("comp2").material("mat5").propertyGroup("Voce").func("int1").set("argunit", new String[]{"K"});
    model.component("comp2").material("mat5").propertyGroup("Voce").set("sigma_voc", "249[MPa]*a(T)");
    model.component("comp2").material("mat5").propertyGroup("Voce").set("beta_voc", "9.3");
    model.component("comp2").material("mat5").propertyGroup("Voce").addInput("temperature");
    model.component("comp2").material("mat5").propertyGroup("HockettSherby").func("int1").set("funcname", "a");
    model.component("comp2").material("mat5").propertyGroup("HockettSherby").func("int1")
         .set("table", new String[][]{{"600[K]", "1"}, {"1100[K]", "0.1"}, {"1643[K]", "0"}});
    model.component("comp2").material("mat5").propertyGroup("HockettSherby").func("int1")
         .set("fununit", new String[]{"1"});
    model.component("comp2").material("mat5").propertyGroup("HockettSherby").func("int1")
         .set("argunit", new String[]{"K"});
    model.component("comp2").material("mat5").propertyGroup("HockettSherby").set("sigma_hoc", "684[MPa]*a(T)");
    model.component("comp2").material("mat5").propertyGroup("HockettSherby").set("m_hoc", "3.9");

    return model;
  }

  public static Model run2(Model model) {
    model.component("comp2").material("mat5").propertyGroup("HockettSherby").set("n_hoc", "0.85");
    model.component("comp2").material("mat5").propertyGroup("HockettSherby").addInput("temperature");
    model.component("comp2").material("mat5").propertyGroup("ArmstrongFrederick").func("int1").set("funcname", "a");
    model.component("comp2").material("mat5").propertyGroup("ArmstrongFrederick").func("int1")
         .set("table", new String[][]{{"600[K]", "1"}, {"1100[K]", "0.1"}, {"1643[K]", "0"}});
    model.component("comp2").material("mat5").propertyGroup("ArmstrongFrederick").func("int1")
         .set("fununit", new String[]{"1"});
    model.component("comp2").material("mat5").propertyGroup("ArmstrongFrederick").func("int1")
         .set("argunit", new String[]{"K"});
    model.component("comp2").material("mat5").propertyGroup("ArmstrongFrederick").set("Ck", "2.070[GPa]*a(T)");
    model.component("comp2").material("mat5").propertyGroup("ArmstrongFrederick").set("gammak", "8.0");
    model.component("comp2").material("mat5").propertyGroup("ArmstrongFrederick").addInput("temperature");
    model.component("comp2").material("mat5").propertyGroup("Norton").set("A_nor", "1.2e-15[1/s]");
    model.component("comp2").material("mat5").propertyGroup("Norton").set("sigRef_nor", "1[MPa]");
    model.component("comp2").material("mat5").propertyGroup("Norton").set("n_nor", "4.5");
    model.component("comp2").material("mat5").propertyGroup("Garofalo").set("A_gar", "1e-6[1/s]");
    model.component("comp2").material("mat5").propertyGroup("Garofalo").set("sigRef_gar", "100[MPa]");
    model.component("comp2").material("mat5").propertyGroup("Garofalo").set("n_gar", "4.6");
    model.component("comp2").material("mat5").propertyGroup("ChabocheViscoplasticity").set("A_cha", "1");
    model.component("comp2").material("mat5").propertyGroup("ChabocheViscoplasticity").set("sigRef_cha", "490[MPa]");
    model.component("comp2").material("mat5").propertyGroup("ChabocheViscoplasticity").set("n_cha", "9");
    model.component("comp2").material("mat5").set("family", "custom");
    model.component("comp2").material("mat5").set("lighting", "cooktorrance");
    model.component("comp2").material("mat5").set("fresnel", 0.9);
    model.component("comp2").material("mat5").set("roughness", 0.3);
    model.component("comp2").material("mat5").set("anisotropy", 0);
    model.component("comp2").material("mat5").set("flipanisotropy", false);
    model.component("comp2").material("mat5").set("metallic", 0);
    model.component("comp2").material("mat5").set("pearl", 0);
    model.component("comp2").material("mat5").set("diffusewrap", 0);
    model.component("comp2").material("mat5").set("clearcoat", 0);
    model.component("comp2").material("mat5").set("reflectance", 0);
    model.component("comp2").material("mat5").set("ambient", "custom");
    model.component("comp2").material("mat5")
         .set("customambient", new double[]{0.6666666666666666, 0.6666666666666666, 0.6666666666666666});
    model.component("comp2").material("mat5").set("diffuse", "custom");
    model.component("comp2").material("mat5")
         .set("customdiffuse", new double[]{0.6666666666666666, 0.6666666666666666, 0.6666666666666666});
    model.component("comp2").material("mat5").set("specular", "custom");
    model.component("comp2").material("mat5")
         .set("customspecular", new double[]{0.7843137254901961, 0.7843137254901961, 0.7843137254901961});
    model.component("comp2").material("mat5").set("noisecolor", "custom");
    model.component("comp2").material("mat5").set("customnoisecolor", new double[]{0, 0, 0});
    model.component("comp2").material("mat5").set("noisescale", 0);
    model.component("comp2").material("mat5").set("noise", "off");
    model.component("comp2").material("mat5").set("noisefreq", 1);
    model.component("comp2").material("mat5").set("normalnoisebrush", "0");
    model.component("comp2").material("mat5").set("normalnoisetype", "0");
    model.component("comp2").material("mat5").set("alpha", 1);
    model.component("comp2").material("mat5").set("anisotropyaxis", new double[]{0, 0, 1});
    model.component("comp2").material().create("mat6", "Common");
    model.component("comp2").material("mat6").propertyGroup().create("Enu", "Young's modulus and Poisson's ratio");
    model.component("comp2").material("mat6").propertyGroup().create("Murnaghan", "Murnaghan");
    model.component("comp2").material("mat6").label("Aluminum");
    model.component("comp2").material("mat6").set("family", "aluminum");
    model.component("comp2").material("mat6").propertyGroup("def")
         .set("relpermeability", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.component("comp2").material("mat6").propertyGroup("def").set("heatcapacity", "900[J/(kg*K)]");
    model.component("comp2").material("mat6").propertyGroup("def")
         .set("thermalconductivity", new String[]{"238[W/(m*K)]", "0", "0", "0", "238[W/(m*K)]", "0", "0", "0", "238[W/(m*K)]"});
    model.component("comp2").material("mat6").propertyGroup("def")
         .set("electricconductivity", new String[]{"3.774e7[S/m]", "0", "0", "0", "3.774e7[S/m]", "0", "0", "0", "3.774e7[S/m]"});
    model.component("comp2").material("mat6").propertyGroup("def")
         .set("relpermittivity", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.component("comp2").material("mat6").propertyGroup("def")
         .set("thermalexpansioncoefficient", new String[]{"23e-6[1/K]", "0", "0", "0", "23e-6[1/K]", "0", "0", "0", "23e-6[1/K]"});
    model.component("comp2").material("mat6").propertyGroup("def").set("density", "2700[kg/m^3]");
    model.component("comp2").material("mat6").propertyGroup("Enu").set("E", "70[GPa]");
    model.component("comp2").material("mat6").propertyGroup("Enu").set("nu", "0.33");
    model.component("comp2").material("mat6").propertyGroup("Murnaghan").set("l", "-250[GPa]");
    model.component("comp2").material("mat6").propertyGroup("Murnaghan").set("m", "-330[GPa]");
    model.component("comp2").material("mat6").propertyGroup("Murnaghan").set("n", "-350[GPa]");
    model.component("comp2").material("mat6").set("family", "aluminum");
    model.component("comp2").material("mat6").selection().set(6);

    model.component("comp2").physics("mbd").create("rd1", "RigidDomain", 3);
    model.component("comp2").physics("mbd").feature("rd1").label("Rigid Material: Rotor with Shaft");
    model.component("comp2").physics("mbd").feature("rd1").selection().set(1, 2, 3, 4, 6, 7, 8, 9, 10, 11, 12);
    model.component("comp2").physics("mbd").feature("rd1").create("af1", "AppliedForce", -1);
    model.component("comp2").physics("mbd").feature("rd1").feature("af1")
         .set("PointOfApplicationType", "CentroidOfSelectedEntities");
    model.component("comp2").physics("mbd").feature("rd1").feature("af1").feature("lcb1").selection().set(45, 53);
    model.component("comp2").physics("mbd").feature("rd1").feature("af1")
         .set("Ft", new String[]{"0", "comp1.rmm.Forcex_Rotor", "comp1.rmm.Forcey_Rotor"});
    model.component("comp2").physics("mbd").feature("rd1").create("am1", "AppliedMoment", -1);
    model.component("comp2").physics("mbd").feature("rd1").feature("am1")
         .set("Mt", new String[]{"comp1.rmm.Tz_Rotor", "0", "0"});
    model.component("comp2").physics("mbd").create("rd2", "RigidDomain", 3);
    model.component("comp2").physics("mbd").feature("rd2").label("Rigid Material: Stator");
    model.component("comp2").physics("mbd").feature("rd2").selection().set(15, 17, 18, 21, 23, 25, 27);
    model.component("comp2").physics("mbd").feature("rd2").create("af1", "AppliedForce", -1);
    model.component("comp2").physics("mbd").feature("rd2").feature("af1")
         .set("Ft", new String[]{"0", "comp1.rmm.Forcex_Stator", "comp1.rmm.Forcey_Stator"});
    model.component("comp2").physics("mbd").feature("rd2").create("am1", "AppliedMoment", -1);
    model.component("comp2").physics("mbd").feature("rd2").feature("am1")
         .set("Mt", new String[]{"comp1.rmm.Tz_Stator", "0", "0"});
    model.component("comp2").physics("mbd").create("att1", "Attachment", 2);
    model.component("comp2").physics("mbd").feature("att1").label("Attachment: Bearing 1");
    model.component("comp2").physics("mbd").feature("att1").selection().set(183, 184, 186, 187);
    model.component("comp2").physics("mbd").create("att2", "Attachment", 2);
    model.component("comp2").physics("mbd").feature("att2").label("Attachment: Bearing 2");
    model.component("comp2").physics("mbd").feature("att2").selection().set(498, 499, 500, 501);
    model.component("comp2").physics("mbd").create("hgj1", "HingeJoint", -1);
    model.component("comp2").physics("mbd").feature("hgj1").label("Hinge Joint: Bearing 1");
    model.component("comp2").physics("mbd").feature("hgj1").set("Source", "att1");
    model.component("comp2").physics("mbd").feature("hgj1").set("Destination", "rd1");
    model.component("comp2").physics("mbd").feature("hgj1").set("JointElasticity", "ElasticJoint");
    model.component("comp2").physics("mbd").feature("hgj1").feature("je1")
         .set("k_u", new String[]{"kb", "0", "0", "0", "kb", "0", "0", "0", "kb"});
    model.component("comp2").physics("mbd").feature("hgj1").feature("je1")
         .set("k_th", new String[]{"kbr", "0", "0", "0", "kbr", "0", "0", "0", "kbr"});
    model.component("comp2").physics("mbd").feature().duplicate("hgj2", "hgj1");
    model.component("comp2").physics("mbd").feature("hgj2").label("Hinge Joint: Bearing 2");
    model.component("comp2").physics("mbd").feature("hgj2").set("Source", "att2");
    model.component("comp2").physics("mbd").create("att3", "Attachment", 2);
    model.component("comp2").physics("mbd").feature("att3").label("Attachment: Foundation 1");
    model.component("comp2").physics("mbd").feature("att3").selection().set(390, 391);
    model.component("comp2").physics("mbd").feature().duplicate("att4", "att3");
    model.component("comp2").physics("mbd").feature("att4").label("Attachment: Foundation 2");
    model.component("comp2").physics("mbd").feature("att4").selection().set(470, 471);
    model.component("comp2").physics("mbd").feature().duplicate("att5", "att4");
    model.component("comp2").physics("mbd").feature("att5").label("Attachment: Foundation 3");
    model.component("comp2").physics("mbd").feature("att5").selection().set(472, 473);
    model.component("comp2").physics("mbd").feature().duplicate("att6", "att5");
    model.component("comp2").physics("mbd").feature("att6").label("Attachment: Foundation 4");
    model.component("comp2").physics("mbd").feature("att6").selection().set(392, 393);
    model.component("comp2").physics("mbd").create("fxj1", "FixedJoint", -1);
    model.component("comp2").physics("mbd").feature("fxj1").label("Fixed Joint: Foundation 1");
    model.component("comp2").physics("mbd").feature("fxj1").set("Source", "fixed");
    model.component("comp2").physics("mbd").feature("fxj1").set("Destination", "att3");
    model.component("comp2").physics("mbd").feature("fxj1").set("JointElasticity", "ElasticJoint");
    model.component("comp2").physics("mbd").feature("fxj1").feature("je1")
         .set("k_u", new String[]{"kf", "0", "0", "0", "kf", "0", "0", "0", "kf"});
    model.component("comp2").physics("mbd").feature("fxj1").feature("je1")
         .set("k_th", new String[]{"kfr", "0", "0", "0", "kfr", "0", "0", "0", "kfr"});
    model.component("comp2").physics("mbd").feature().duplicate("fxj2", "fxj1");
    model.component("comp2").physics("mbd").feature("fxj2").label("Fixed Joint: Foundation 2");
    model.component("comp2").physics("mbd").feature("fxj2").set("Destination", "att4");
    model.component("comp2").physics("mbd").feature().duplicate("fxj3", "fxj2");
    model.component("comp2").physics("mbd").feature("fxj3").label("Fixed Joint: Foundation 3");
    model.component("comp2").physics("mbd").feature("fxj3").set("Destination", "att5");
    model.component("comp2").physics("mbd").feature().duplicate("fxj4", "fxj3");
    model.component("comp2").physics("mbd").feature("fxj4").label("Fixed Joint: Foundation 4");
    model.component("comp2").physics("mbd").feature("fxj4").set("Destination", "att6");

    model.component("comp1").common().create("rot1", "RotatingDomain");
    model.component("comp1").common("rot1").selection().set(17, 18, 19, 20);
    model.component("comp1").common("rot1").set("rotationAngle", "comp2.mbd.hgj1.th");
    model.component("comp1").common("rot1").set("rotationAxisBasePoint", new String[]{"0", "dy", "0"});

    model.study("std1").feature("time").set("tlist", "range(0,0.001,0.7)");

    model.sol().create("sol1");
    model.sol("sol1").study("std1");

    model.study("std1").feature("time").set("notlistsolnum", 1);
    model.study("std1").feature("time").set("notsolnum", "auto");
    model.study("std1").feature("time").set("listsolnum", 1);
    model.study("std1").feature("time").set("solnum", "auto");

    model.sol("sol1").create("st1", "StudyStep");
    model.sol("sol1").feature("st1").set("study", "std1");
    model.sol("sol1").feature("st1").set("studystep", "time");
    model.sol("sol1").create("v1", "Variables");
    model.sol("sol1").feature("v1").feature("comp2_mbd_rd2_u").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp2_mbd_fxj4_u2").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp2_mbd_rd1_u").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp2_mbd_fxj4_u1").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp2_mbd_fxj2_u1").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp2_mbd_fxj2_th1").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp2_mbd_fxj2_u2").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp2_mbd_fxj4_u3").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp2_mbd_fxj2_th2").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp2_mbd_fxj2_th3").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp2_mbd_hgj2_th2").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp2_mbd_fxj3_th3").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp2_mbd_fxj3_th1").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp2_mbd_hgj2_th3").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp2_mbd_fxj3_th2").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp2_mbd_fxj2_u3").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp2_mbd_fxj1_th1").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp2_mbd_att_rot").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp2_mbd_fxj1_th2").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp2_mbd_hgj1_u3").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp2_mbd_rd2_a").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp2_mbd_rd1_b").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp2_mbd_hgj1_u1").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp2_mbd_rd1_a").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp2_mbd_att_disp").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp2_mbd_hgj1_u2").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp2_mbd_fxj1_th3").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp2_mbd_rd2_b").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp2_mbd_hgj2_th").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp2_mbd_fxj3_u1").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp2_mbd_fxj3_u3").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp2_mbd_fxj1_u1").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp2_mbd_fxj3_u2").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp2_u").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp2_mbd_fxj1_u2").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp2_mbd_fxj1_u3").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp2_mbd_hgj2_u2").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp2_mbd_hgj2_u3").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp2_mbd_hgj2_u1").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp2_mbd_fxj4_th1").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp2_mbd_hgj1_th3").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp2_mbd_fxj4_th2").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp2_mbd_hgj1_th2").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp2_mbd_fxj4_th3").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp2_mbd_hgj1_th").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp2_mbd_fxj2_th1").set("resscalemethod", "parent");
    model.sol("sol1").feature("v1").feature("comp2_mbd_fxj2_th2").set("resscalemethod", "parent");
    model.sol("sol1").feature("v1").feature("comp2_mbd_fxj2_th3").set("resscalemethod", "parent");
    model.sol("sol1").feature("v1").feature("comp2_mbd_hgj2_th2").set("resscalemethod", "parent");
    model.sol("sol1").feature("v1").feature("comp2_mbd_fxj3_th3").set("resscalemethod", "parent");
    model.sol("sol1").feature("v1").feature("comp2_mbd_fxj3_th1").set("resscalemethod", "parent");
    model.sol("sol1").feature("v1").feature("comp2_mbd_hgj2_th3").set("resscalemethod", "parent");
    model.sol("sol1").feature("v1").feature("comp2_mbd_fxj3_th2").set("resscalemethod", "parent");
    model.sol("sol1").feature("v1").feature("comp2_mbd_fxj1_th1").set("resscalemethod", "parent");
    model.sol("sol1").feature("v1").feature("comp2_mbd_att_rot").set("resscalemethod", "parent");
    model.sol("sol1").feature("v1").feature("comp2_mbd_fxj1_th2").set("resscalemethod", "parent");
    model.sol("sol1").feature("v1").feature("comp2_mbd_rd2_a").set("resscalemethod", "parent");
    model.sol("sol1").feature("v1").feature("comp2_mbd_rd1_b").set("resscalemethod", "parent");
    model.sol("sol1").feature("v1").feature("comp2_mbd_rd1_a").set("resscalemethod", "parent");
    model.sol("sol1").feature("v1").feature("comp2_mbd_att_disp").set("resscalemethod", "parent");
    model.sol("sol1").feature("v1").feature("comp2_mbd_fxj1_th3").set("resscalemethod", "parent");
    model.sol("sol1").feature("v1").feature("comp2_mbd_rd2_b").set("resscalemethod", "parent");
    model.sol("sol1").feature("v1").feature("comp2_mbd_fxj4_th1").set("resscalemethod", "parent");
    model.sol("sol1").feature("v1").feature("comp2_mbd_hgj1_th3").set("resscalemethod", "parent");
    model.sol("sol1").feature("v1").feature("comp2_mbd_fxj4_th2").set("resscalemethod", "parent");
    model.sol("sol1").feature("v1").feature("comp2_mbd_hgj1_th2").set("resscalemethod", "parent");
    model.sol("sol1").feature("v1").feature("comp2_mbd_fxj4_th3").set("resscalemethod", "parent");
    model.sol("sol1").feature("v1").feature("comp2_mbd_hgj1_th").set("resscalemethod", "parent");
    model.sol("sol1").feature("v1").feature("comp2_mbd_hgj2_th").set("resscalemethod", "parent");
    model.sol("sol1").feature("v1").feature("comp2_mbd_rd2_u").set("scaleval", "1e-2*0.34076091325150576");
    model.sol("sol1").feature("v1").feature("comp2_mbd_fxj4_u2").set("scaleval", "1e-2*0.34076091325150576");
    model.sol("sol1").feature("v1").feature("comp2_mbd_rd1_u").set("scaleval", "1e-2*0.34076091325150576");
    model.sol("sol1").feature("v1").feature("comp2_mbd_fxj4_u1").set("scaleval", "1e-2*0.34076091325150576");
    model.sol("sol1").feature("v1").feature("comp2_mbd_fxj2_u1").set("scaleval", "1e-2*0.34076091325150576");
    model.sol("sol1").feature("v1").feature("comp2_mbd_fxj2_th1").set("scaleval", "0.1");
    model.sol("sol1").feature("v1").feature("comp2_mbd_fxj2_u2").set("scaleval", "1e-2*0.34076091325150576");
    model.sol("sol1").feature("v1").feature("comp2_mbd_fxj4_u3").set("scaleval", "1e-2*0.34076091325150576");
    model.sol("sol1").feature("v1").feature("comp2_mbd_fxj2_th2").set("scaleval", "0.1");
    model.sol("sol1").feature("v1").feature("comp2_mbd_fxj2_th3").set("scaleval", "0.1");
    model.sol("sol1").feature("v1").feature("comp2_mbd_hgj2_th2").set("scaleval", "0.1");
    model.sol("sol1").feature("v1").feature("comp2_mbd_fxj3_th3").set("scaleval", "0.1");
    model.sol("sol1").feature("v1").feature("comp2_mbd_fxj3_th1").set("scaleval", "0.1");
    model.sol("sol1").feature("v1").feature("comp2_mbd_hgj2_th3").set("scaleval", "0.1");
    model.sol("sol1").feature("v1").feature("comp2_mbd_fxj3_th2").set("scaleval", "0.1");
    model.sol("sol1").feature("v1").feature("comp2_mbd_fxj2_u3").set("scaleval", "1e-2*0.34076091325150576");
    model.sol("sol1").feature("v1").feature("comp2_mbd_fxj1_th1").set("scaleval", "0.1");
    model.sol("sol1").feature("v1").feature("comp2_mbd_att_rot").set("scaleval", "0.1");
    model.sol("sol1").feature("v1").feature("comp2_mbd_fxj1_th2").set("scaleval", "0.1");
    model.sol("sol1").feature("v1").feature("comp2_mbd_hgj1_u3").set("scaleval", "1e-2*0.34076091325150576");
    model.sol("sol1").feature("v1").feature("comp2_mbd_rd2_a").set("scaleval", "0.1");
    model.sol("sol1").feature("v1").feature("comp2_mbd_rd1_b").set("scaleval", "0.1");
    model.sol("sol1").feature("v1").feature("comp2_mbd_hgj1_u1").set("scaleval", "1e-2*0.34076091325150576");
    model.sol("sol1").feature("v1").feature("comp2_mbd_rd1_a").set("scaleval", "0.1");
    model.sol("sol1").feature("v1").feature("comp2_mbd_att_disp").set("scaleval", "0.0034076091325150578");
    model.sol("sol1").feature("v1").feature("comp2_mbd_hgj1_u2").set("scaleval", "1e-2*0.34076091325150576");
    model.sol("sol1").feature("v1").feature("comp2_mbd_fxj1_th3").set("scaleval", "0.1");
    model.sol("sol1").feature("v1").feature("comp2_mbd_rd2_b").set("scaleval", "0.1");
    model.sol("sol1").feature("v1").feature("comp2_mbd_hgj2_th").set("scaleval", "0.1");
    model.sol("sol1").feature("v1").feature("comp2_mbd_fxj3_u1").set("scaleval", "1e-2*0.34076091325150576");
    model.sol("sol1").feature("v1").feature("comp2_mbd_fxj3_u3").set("scaleval", "1e-2*0.34076091325150576");
    model.sol("sol1").feature("v1").feature("comp2_mbd_fxj1_u1").set("scaleval", "1e-2*0.34076091325150576");
    model.sol("sol1").feature("v1").feature("comp2_mbd_fxj3_u2").set("scaleval", "1e-2*0.34076091325150576");
    model.sol("sol1").feature("v1").feature("comp2_u").set("scaleval", "1e-2*0.34076091325150576");
    model.sol("sol1").feature("v1").feature("comp2_mbd_fxj1_u2").set("scaleval", "1e-2*0.34076091325150576");
    model.sol("sol1").feature("v1").feature("comp2_mbd_fxj1_u3").set("scaleval", "1e-2*0.34076091325150576");
    model.sol("sol1").feature("v1").feature("comp2_mbd_hgj2_u2").set("scaleval", "1e-2*0.34076091325150576");
    model.sol("sol1").feature("v1").feature("comp2_mbd_hgj2_u3").set("scaleval", "1e-2*0.34076091325150576");
    model.sol("sol1").feature("v1").feature("comp2_mbd_hgj2_u1").set("scaleval", "1e-2*0.34076091325150576");
    model.sol("sol1").feature("v1").feature("comp2_mbd_fxj4_th1").set("scaleval", "0.1");
    model.sol("sol1").feature("v1").feature("comp2_mbd_hgj1_th3").set("scaleval", "0.1");
    model.sol("sol1").feature("v1").feature("comp2_mbd_fxj4_th2").set("scaleval", "0.1");
    model.sol("sol1").feature("v1").feature("comp2_mbd_hgj1_th2").set("scaleval", "0.1");
    model.sol("sol1").feature("v1").feature("comp2_mbd_fxj4_th3").set("scaleval", "0.1");
    model.sol("sol1").feature("v1").feature("comp2_mbd_hgj1_th").set("scaleval", "0.1");
    model.sol("sol1").feature("v1").set("control", "time");
    model.sol("sol1").create("t1", "Time");
    model.sol("sol1").feature("t1").set("tlist", "range(0,0.001,0.7)");
    model.sol("sol1").feature("t1").set("plot", "off");
    model.sol("sol1").feature("t1").set("plotgroup", "Default");
    model.sol("sol1").feature("t1").set("plotfreq", "tout");
    model.sol("sol1").feature("t1").set("probesel", "all");
    model.sol("sol1").feature("t1").set("probes", new String[]{});
    model.sol("sol1").feature("t1").set("probefreq", "tsteps");
    model.sol("sol1").feature("t1").set("tout", "tstepsclosest");
    model.sol("sol1").feature("t1").set("rtol", 0.001);
    model.sol("sol1").feature("t1").set("atolglobalvaluemethod", "factor");
    model.sol("sol1").feature("t1").set("atolglobalmethod", "scaled");
    model.sol("sol1").feature("t1").set("atolglobalfactor", 0.1);
    model.sol("sol1").feature("t1").set("atolglobalvaluemethod", "factor");
    model.sol("sol1").feature("t1").set("eventtol", 0.01);
    model.sol("sol1").feature("t1").set("reacf", true);
    model.sol("sol1").feature("t1").set("storeudot", true);
    model.sol("sol1").feature("t1").set("tstepsbdf", "strict");
    model.sol("sol1").feature("t1").set("endtimeinterpolation", true);
    model.sol("sol1").feature("t1").set("estrat", "exclude");
    model.sol("sol1").feature("t1").set("maxorder", 2);
    model.sol("sol1").feature("t1").set("minorder", 1);
    model.sol("sol1").feature("t1").set("rescaleafterinitbw", false);
    model.sol("sol1").feature("t1").set("bwinitstepfrac", "0.001");
    model.sol("sol1").feature("t1").set("control", "time");
    model.sol("sol1").feature("t1").feature("aDef").set("cachepattern", true);
    model.sol("sol1").feature("t1").create("se1", "Segregated");
    model.sol("sol1").feature("t1").feature("se1").feature().remove("ssDef");
    model.sol("sol1").feature("t1").feature("se1").create("ss1", "SegregatedStep");
    model.sol("sol1").feature("t1").feature("se1").feature("ss1")
         .set("segvar", new String[]{"comp2_u", "comp2_mbd_rd1_u", "comp2_mbd_rd1_a", "comp2_mbd_rd1_b", "comp2_mbd_rd2_u", "comp2_mbd_rd2_a", "comp2_mbd_rd2_b", "comp2_mbd_hgj1_th", "comp2_mbd_hgj1_u1", "comp2_mbd_hgj1_u2", 
         "comp2_mbd_hgj1_u3", "comp2_mbd_hgj1_th2", "comp2_mbd_hgj1_th3", "comp2_mbd_hgj2_th", "comp2_mbd_hgj2_u1", "comp2_mbd_hgj2_u2", "comp2_mbd_hgj2_u3", "comp2_mbd_hgj2_th2", "comp2_mbd_hgj2_th3", "comp2_mbd_fxj1_u1", 
         "comp2_mbd_fxj1_u2", "comp2_mbd_fxj1_u3", "comp2_mbd_fxj1_th1", "comp2_mbd_fxj1_th2", "comp2_mbd_fxj1_th3", "comp2_mbd_fxj2_u1", "comp2_mbd_fxj2_u2", "comp2_mbd_fxj2_u3", "comp2_mbd_fxj2_th1", "comp2_mbd_fxj2_th2", 
         "comp2_mbd_fxj2_th3", "comp2_mbd_fxj3_u1", "comp2_mbd_fxj3_u2", "comp2_mbd_fxj3_u3", "comp2_mbd_fxj3_th1", "comp2_mbd_fxj3_th2", "comp2_mbd_fxj3_th3", "comp2_mbd_fxj4_u1", "comp2_mbd_fxj4_u2", "comp2_mbd_fxj4_u3", 
         "comp2_mbd_fxj4_th1", "comp2_mbd_fxj4_th2", "comp2_mbd_fxj4_th3", "comp2_mbd_att_disp", "comp2_mbd_att_rot"});
    model.sol("sol1").feature("t1").feature("se1").feature("ss1").set("linsolver", "dDef");
    model.sol("sol1").feature("t1").feature("se1").feature("ss1").label("Multibody Dynamics");
    model.sol("sol1").feature("t1").feature("se1").create("ss2", "SegregatedStep");
    model.sol("sol1").feature("t1").feature("se1").feature("ss2").set("segvar", new String[]{"comp1_A"});
    model.sol("sol1").feature("t1").feature("se1").feature("ss2").set("linsolver", "dDef");
    model.sol("sol1").feature("t1").feature("se1").feature("ss2").label("Rotating Machinery, Magnetic");
    model.sol("sol1").feature("t1").feature().remove("fcDef");
    model.sol("sol1").attach("std1");
    model.sol("sol1").feature("t1").set("tstepsbdf", "intermediate");
    model.sol("sol1").feature("t1").set("maxstepconstraintbdf", "const");
    model.sol("sol1").feature("t1").set("maxstepbdf", "0.0002");
    model.sol("sol1").feature("t1").feature("dDef").set("linsolver", "pardiso");
    model.sol("sol1").feature("t1").feature("se1").feature("ss1").set("subjtech", "once");
    model.sol("sol1").feature("t1").feature("se1").feature("ss1").set("subtermconst", "tol");
    model.sol("sol1").feature("t1").feature("se1").feature("ss1").set("subntolfact", 1);
    model.sol("sol1").feature("t1").feature("se1").feature("ss2").set("subjtech", "onevery");
    model.sol("sol1").feature("t1").feature("se1").feature("ss2").set("subtermconst", "tol");
    model.sol("sol1").feature("t1").feature("se1").feature("ss2").set("maxsubiter", 8);
    model.sol("sol1").feature("t1").feature("se1").feature("ss2").set("subntolfact", "1e-3");

    model.study("std1").setGenPlots(false);

    model.sol("sol1").runAll();

    model.result().create("pg1", "PlotGroup2D");
    model.result("pg1").run();
    model.result("pg1").label("Magnetic Flux Density Norm");
    model.result("pg1").set("frametype", "spatial");
    model.result("pg1").create("surf1", "Surface");
    model.result("pg1").run();
    model.result("pg1").create("arws1", "ArrowSurface");
    model.result("pg1").feature("arws1").set("xnumber", 50);
    model.result("pg1").feature("arws1").set("ynumber", 50);
    model.result("pg1").feature("arws1").set("scaleactive", true);
    model.result("pg1").feature("arws1").set("scale", 0.055);
    model.result().dataset("dset1").selection().geom("geom1", 2);
    model.result().dataset("dset1").selection().geom("geom1", 2);
    model.result().dataset("dset1").selection().set(3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 16, 17, 18, 19, 20);
    model.result().dataset().duplicate("dset3", "dset2");
    model.result().dataset("dset3").selection().geom("geom2", 3);
    model.result().dataset("dset3").selection().geom("geom2", 3);
    model.result().dataset("dset3").selection().set(13, 29, 30, 31, 32, 33, 34, 35, 36);
    model.result().dataset().duplicate("dset4", "dset3");
    model.result().dataset("dset4").selection().geom("geom2", 3);
    model.result().dataset("dset4").selection().set();
    model.result().dataset("dset4").selection().geom("geom2", 3);
    model.result().dataset("dset4").selection().set(1, 2, 3, 4, 6, 7, 8, 9, 10, 11, 12, 17, 21, 25, 27);
    model.result("pg1").run();
    model.result("pg1").run();
    model.result().create("pg2", "PlotGroup1D");
    model.result("pg2").run();
    model.result("pg2").label("Electromagnetic Torque (Rotor)");
    model.result("pg2").set("titletype", "manual");
    model.result("pg2").set("title", "Electromagnetic Torque (Rotor)");
    model.result("pg2").create("glob1", "Global");
    model.result("pg2").feature("glob1").set("markerpos", "datapoints");
    model.result("pg2").feature("glob1").set("linewidth", "preference");
    model.result("pg2").feature("glob1").setIndex("expr", "comp1.rmm.Tz_Rotor", 0);
    model.result("pg2").feature("glob1").setIndex("unit", "N*m", 0);
    model.result("pg2").feature("glob1").setIndex("descr", "Torque, z-component", 0);
    model.result("pg2").feature("glob1").set("linewidth", 2);
    model.result("pg2").run();
    model.result("pg2").set("showlegends", false);
    model.result("pg2").run();
    model.result().duplicate("pg3", "pg2");
    model.result("pg3").run();
    model.result("pg3").label("Rotor Speed");
    model.result("pg3").set("title", "Rotor Speed");
    model.result("pg3").run();
    model.result("pg3").feature("glob1").setIndex("expr", "comp2.mbd.hgj1.tht", 0);
    model.result("pg3").feature("glob1").setIndex("unit", "rad/s", 0);
    model.result("pg3").feature("glob1").setIndex("descr", "Relative angular velocity", 0);

    return model;
  }

  public static Model run3(Model model) {
    model.result("pg3").run();
    model.result("pg3").run();
    model.result().duplicate("pg4", "pg3");
    model.result("pg4").run();
    model.result("pg4").label("Electromagnetic Forces (Rotor)");
    model.result("pg4").set("title", "Electromagnetic Forces (Rotor)");
    model.result("pg4").run();
    model.result("pg4").feature("glob1").setIndex("expr", "rmm.Forcex_Rotor", 0);
    model.result("pg4").feature("glob1").setIndex("unit", "N", 0);
    model.result("pg4").feature("glob1").setIndex("descr", "Electromagnetic force, x-component", 0);
    model.result("pg4").feature("glob1").setIndex("expr", "rmm.Forcey_Rotor", 1);
    model.result("pg4").feature("glob1").setIndex("unit", "N", 1);
    model.result("pg4").feature("glob1").setIndex("descr", "Electromagnetic force, y-component", 1);
    model.result("pg4").run();
    model.result("pg4").set("showlegends", true);
    model.result("pg4").set("legendpos", "lowerleft");
    model.result("pg4").set("ylabelactive", true);
    model.result("pg4").set("ylabel", "Force (N)");
    model.result("pg4").run();
    model.result().duplicate("pg5", "pg4");
    model.result("pg5").run();
    model.result("pg5").label("Electromagnetic Forces (Rotor): Frequency");
    model.result("pg5").set("ylabelactive", false);
    model.result("pg5").set("legendpos", "upperright");
    model.result("pg5").run();
    model.result("pg5").feature("glob1").set("xdata", "fourier");
    model.result("pg5").feature("glob1").set("fouriershow", "spectrum");
    model.result("pg5").feature("glob1").set("freqrangeactive", true);
    model.result("pg5").feature("glob1").set("freqmax", 300);
    model.result("pg5").run();
    model.result("pg4").run();
    model.result().duplicate("pg6", "pg4");
    model.result("pg6").run();
    model.result("pg6").label("Forces (Bearing-1)");
    model.result("pg6").set("title", "Forces (Bearing-1)");
    model.result("pg6").run();
    model.result("pg6").feature("glob1").setIndex("expr", "comp2.mbd.hgj1.Fx", 0);
    model.result("pg6").feature("glob1").setIndex("unit", "N", 0);
    model.result("pg6").feature("glob1").setIndex("descr", "Joint force, x-component", 0);
    model.result("pg6").feature("glob1").setIndex("expr", "comp2.mbd.hgj1.Fy", 1);
    model.result("pg6").feature("glob1").setIndex("unit", "N", 1);
    model.result("pg6").feature("glob1").setIndex("descr", "Joint force, y-component", 1);
    model.result("pg6").feature("glob1").setIndex("expr", "comp2.mbd.hgj1.Fz", 2);
    model.result("pg6").feature("glob1").setIndex("unit", "N", 2);
    model.result("pg6").feature("glob1").setIndex("descr", "Joint force, z-component", 2);
    model.result("pg6").feature("glob1").set("legendmethod", "manual");
    model.result("pg6").feature("glob1").setIndex("legends", "x-component", 0);
    model.result("pg6").feature("glob1").setIndex("legends", "y-component", 1);
    model.result("pg6").feature("glob1").setIndex("legends", "z-component", 2);
    model.result("pg6").run();
    model.result("pg6").set("legendpos", "upperleft");
    model.result("pg6").run();
    model.result().duplicate("pg7", "pg6");
    model.result("pg7").run();
    model.result("pg7").label("Forces (Bearing-2)");
    model.result("pg7").set("title", "Forces (Bearing-2)");
    model.result("pg7").run();
    model.result("pg7").feature("glob1").setIndex("expr", "comp2.mbd.hgj2.Fx", 0);
    model.result("pg7").feature("glob1").setIndex("unit", "N", 0);
    model.result("pg7").feature("glob1").setIndex("descr", "Joint force, x-component", 0);
    model.result("pg7").feature("glob1").setIndex("expr", "comp2.mbd.hgj2.Fy", 1);
    model.result("pg7").feature("glob1").setIndex("unit", "N", 1);
    model.result("pg7").feature("glob1").setIndex("descr", "Joint force, y-component", 1);
    model.result("pg7").feature("glob1").setIndex("expr", "comp2.mbd.hgj2.Fz", 2);
    model.result("pg7").feature("glob1").setIndex("unit", "N", 2);
    model.result("pg7").feature("glob1").setIndex("descr", "Joint force, z-component", 2);
    model.result("pg7").run();
    model.result("pg7").run();
    model.result().duplicate("pg8", "pg7");
    model.result("pg8").run();
    model.result("pg8").label("Forces (Foundation-1)");
    model.result("pg8").set("title", "Forces (Foundation-1)");
    model.result("pg8").run();
    model.result("pg8").feature("glob1").setIndex("expr", "comp2.mbd.fxj1.Fx", 0);
    model.result("pg8").feature("glob1").setIndex("unit", "N", 0);
    model.result("pg8").feature("glob1").setIndex("descr", "Joint force, x-component", 0);
    model.result("pg8").feature("glob1").setIndex("expr", "comp2.mbd.fxj1.Fy", 1);
    model.result("pg8").feature("glob1").setIndex("unit", "N", 1);
    model.result("pg8").feature("glob1").setIndex("descr", "Joint force, y-component", 1);
    model.result("pg8").feature("glob1").setIndex("expr", "comp2.mbd.fxj1.Fz", 2);
    model.result("pg8").feature("glob1").setIndex("unit", "N", 2);
    model.result("pg8").feature("glob1").setIndex("descr", "Joint force, z-component", 2);
    model.result("pg8").run();
    model.result("pg8").run();
    model.result().duplicate("pg9", "pg8");
    model.result("pg9").run();
    model.result("pg9").label("Forces (Foundation-1): Frequency");
    model.result("pg9").run();
    model.result("pg9").feature("glob1").set("xdata", "fourier");
    model.result("pg9").feature("glob1").set("fouriershow", "spectrum");
    model.result("pg9").feature("glob1").set("freqrangeactive", true);
    model.result("pg9").feature("glob1").set("freqmax", 300);
    model.result("pg9").run();
    model.result("pg9").set("legendpos", "upperright");
    model.result("pg9").set("ylabelactive", false);
    model.result("pg9").run();
    model.result().create("pg10", "PlotGroup1D");
    model.result("pg10").run();
    model.result("pg10").label("Rotor Orbits");
    model.result("pg10").set("titletype", "manual");
    model.result("pg10").set("title", "Rotor Orbits");
    model.result("pg10").set("data", "dset2");
    model.result("pg10").create("glob1", "Global");
    model.result("pg10").feature("glob1").set("markerpos", "datapoints");
    model.result("pg10").feature("glob1").set("linewidth", "preference");
    model.result("pg10").feature("glob1").setIndex("expr", "mbd.hgj1.u2", 0);
    model.result("pg10").feature("glob1").setIndex("unit", "mm", 0);
    model.result("pg10").feature("glob1").setIndex("descr", "Displacement (y direction)", 0);
    model.result("pg10").feature("glob1").set("xdata", "expr");
    model.result("pg10").feature("glob1").set("xdataexpr", "mbd.hgj1.u3");
    model.result("pg10").feature("glob1").set("xdataunit", "mm");
    model.result("pg10").feature("glob1").set("xdatadescractive", true);
    model.result("pg10").feature("glob1").set("xdatadescr", "Displacement (z direction)");
    model.result("pg10").feature("glob1").create("col1", "Color");
    model.result("pg10").run();
    model.result("pg10").feature("glob1").feature("col1").set("expr", "t");
    model.result("pg10").run();
    model.result("pg10").feature().duplicate("glob2", "glob1");
    model.result("pg10").run();
    model.result("pg10").feature("glob2").setIndex("expr", "mbd.hgj2.u2", 0);
    model.result("pg10").feature("glob2").setIndex("unit", "mm", 0);
    model.result("pg10").feature("glob2").setIndex("descr", "Displacement (y direction)", 0);
    model.result("pg10").feature("glob2").set("xdataexpr", "mbd.hgj2.u3");
    model.result("pg10").run();
    model.result("pg10").create("ann1", "Annotation");
    model.result("pg10").feature("ann1").set("text", "Bearing 1");
    model.result("pg10").feature("ann1").set("posxexpr", "1e-3");
    model.result("pg10").feature("ann1").set("posyexpr", "-1e-3");
    model.result("pg10").feature("ann1").set("showpoint", false);
    model.result("pg10").feature().duplicate("ann2", "ann1");
    model.result("pg10").run();
    model.result("pg10").feature("ann2").set("text", "Bearing 2");
    model.result("pg10").feature("ann2").set("posxexpr", "-3e-3");
    model.result("pg10").feature("ann2").set("posyexpr", "1.5e-3");
    model.result("pg10").run();
    model.result("pg10").set("showlegends", false);
    model.result("pg10").set("preserveaspect", true);
    model.result("pg10").run();
    model.result().create("pg11", "PlotGroup3D");
    model.result("pg11").run();
    model.result("pg11").set("data", "dset3");
    model.result("pg11").label("Housing Stress");
    model.result("pg11").create("surf1", "Surface");
    model.result("pg11").feature("surf1").set("expr", "mbd.mises");
    model.result("pg11").feature("surf1").set("rangecoloractive", true);
    model.result("pg11").feature("surf1").set("rangecolormax", "5e4");
    model.result("pg11").feature("surf1").set("colortable", "TrafficLightClassic");
    model.result("pg11").feature("surf1").create("def1", "Deform");
    model.result("pg11").run();
    model.result("pg11").feature("surf1").feature("def1").set("scaleactive", true);
    model.result("pg11").feature("surf1").feature("def1").set("scale", 2000);
    model.result("pg11").run();
    model.result("pg11").run();
    model.result().duplicate("pg12", "pg11");
    model.result("pg12").run();
    model.result("pg12").label("Housing Stress (with Rotor)");
    model.result("pg12").set("data", "dset2");
    model.result("pg12").set("frametype", "spatial");
    model.result("pg12").run();
    model.result("pg12").feature("surf1").set("data", "dset3");
    model.result("pg12").feature("surf1").set("solutionparams", "parent");
    model.result("pg12").run();
    model.result("pg12").feature("surf1").feature("def1").set("scale", 1);
    model.result("pg12").run();
    model.result("pg12").create("con1", "Contour");
    model.result("pg12").feature("con1").set("data", "dset3");
    model.result("pg12").feature("con1").set("solutionparams", "parent");
    model.result("pg12").feature("con1").set("expr", "mbd.mises");
    model.result("pg12").feature("con1").set("number", 10);
    model.result("pg12").feature("con1").set("coloring", "uniform");
    model.result("pg12").feature("con1").set("color", "black");
    model.result("pg12").feature("con1").set("titletype", "none");
    model.result("pg12").feature("con1").set("colorlegend", false);
    model.result("pg12").feature("con1").create("def1", "Deform");
    model.result("pg12").run();
    model.result("pg12").feature("con1").feature("def1").set("scaleactive", true);
    model.result("pg12").feature("con1").feature("def1").set("scale", 1);
    model.result("pg12").run();
    model.result("pg12").create("surf2", "Surface");
    model.result("pg12").feature("surf2").set("data", "dset4");
    model.result("pg12").feature("surf2").set("solutionparams", "parent");
    model.result("pg12").feature("surf2").set("expr", "mbd.vel");
    model.result("pg12").feature("surf2").set("colortable", "AuroraBorealis");
    model.result("pg12").feature("surf2").create("def1", "Deform");
    model.result("pg12").run();
    model.result("pg12").feature("surf2").feature("def1").set("scaleactive", true);
    model.result("pg12").feature("surf2").feature("def1").set("scale", 1);
    model.result("pg12").run();
    model.result("pg12").set("legendpos", "rightdouble");
    model.result("pg12").run();
    model.result().export().create("anim1", "Animation");
    model.result().export("anim1").set("target", "player");
    model.result().export("anim1").set("fontsize", "9");
    model.result().export("anim1").set("colortheme", "globaltheme");
    model.result().export("anim1").set("customcolor", new double[]{1, 1, 1});
    model.result().export("anim1").set("background", "color");
    model.result().export("anim1").set("gltfincludelines", "on");
    model.result().export("anim1").set("title1d", "on");
    model.result().export("anim1").set("legend1d", "on");
    model.result().export("anim1").set("logo1d", "on");
    model.result().export("anim1").set("options1d", "on");
    model.result().export("anim1").set("title2d", "on");
    model.result().export("anim1").set("legend2d", "on");
    model.result().export("anim1").set("logo2d", "on");
    model.result().export("anim1").set("options2d", "off");
    model.result().export("anim1").set("title3d", "on");
    model.result().export("anim1").set("legend3d", "on");
    model.result().export("anim1").set("logo3d", "on");
    model.result().export("anim1").set("options3d", "off");
    model.result().export("anim1").set("axisorientation", "on");
    model.result().export("anim1").set("grid", "on");
    model.result().export("anim1").set("axes1d", "on");
    model.result().export("anim1").set("axes2d", "on");
    model.result().export("anim1").set("showgrid", "on");
    model.result().export("anim1").showFrame();
    model.result().export("anim1").set("maxframes", 50);
    model.result().export().duplicate("anim2", "anim1");
    model.result().export("anim2").showFrame();
    model.result().export("anim2").set("plotgroup", "pg11");
    model.result().export("anim2").set("looplevelinput", "interp");
    model.result().export("anim2").set("interp", "range(0,0.01,0.7)");
    model.result().export("anim2").set("maxframes", 70);
    model.result().export().duplicate("anim3", "anim2");
    model.result().export("anim3").showFrame();
    model.result().export("anim3").set("plotgroup", "pg12");
    model.result("pg12").run();

    model.title("Modeling Vibration in an Induction Motor");

    model
         .description("This is an example of a model of an induction motor in which the eddy currents are induced in the rotor by the time harmonic currents on the stator windings and the rotation of the rotor. The air gap between the rotor and stator is assumed asymmetric and the resulting vibrations in the motor are analyzed.\n\nIn this example, electromagnetic simulation of an induction motor in performed in 2D whereas the multibody dynamics simulation in performed in 3D. The rotational torque, when an alternating current is passed through the stator windings, are calculated as a function of time is used in multibody dynamics in order to compute the rotor speed accounting for inertial effects.");

    model.label("induction_motor_vibration.mph");

    model.result("pg12").run();
    model.result().export("anim1").showFrame();
    model.result().export("anim2").showFrame();
    model.result().export("anim2").run();
    model.result().export("anim2").set("plotgroup", "pg10");
    model.result().export("anim2").run();
    model.result().export("anim3").showFrame();
    model.result().export("anim2").showFrame();
    model.result().export("anim1").showFrame();
    model.result().export("anim2").showFrame();
    model.result().export("anim3").showFrame();
    model.result().export("anim2").showFrame();
    model.result().export("anim2").set("plotgroup", "pg12");
    model.result().export("anim3").showFrame();
    model.result().export("anim2").showFrame();
    model.result().export("anim2").run();
    model.result().export("anim2").run();
    model.result().export("anim2").run();

    model.component("comp2").baseSystem("none");
    model.component("comp2").baseSystem("emu");
    model.component("comp2").baseSystem("esu");
    model.component("comp2").baseSystem("fps");
    model.component("comp2").baseSystem("fps_abs");
    model.component("comp2").baseSystem("ips");
    model.component("comp2").baseSystem("ips_abs");
    model.component("comp2").baseSystem("psi");
    model.component("comp2").baseSystem("psi_abs");
    model.component("comp2").baseSystem("none");
    model.component("comp2").baseSystem("psi_abs");
    model.component("comp2").baseSystem("psi");
    model.component("comp2").baseSystem("ips_abs");
    model.component("comp2").baseSystem("ips");
    model.component("comp2").baseSystem("fps_abs");
    model.component("comp2").baseSystem("fps");
    model.component("comp2").baseSystem("esu");
    model.component("comp2").baseSystem("emu");
    model.component("comp2").baseSystem("mpa");
    model.component("comp2").baseSystem("cgs");
    model.component("comp2").baseSystem("bft_abs");
    model.component("comp2").baseSystem("bft");
    model.component("comp2").baseSystem("SI");
    model.component("comp2").baseSystem(null);

    model.result("pg2").run();
    model.result().export("anim3").showFrame();
    model.result().export("anim3").run();
    model.result().export("anim3").run();
    model.result().export("anim3").set("framesel", "all");
    model.result("pg6").run();

    return model;
  }

  public static void main(String[] args) {
    Model model = run();
    model = run2(model);
    run3(model);
  }

}
