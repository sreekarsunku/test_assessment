package com.project.cba.fdp.pub;

public class HomeLoanCal {

	static float emi_calculator(float p, float r, float t) {
		float emi;

		r = r / (12 * 100);
		t = t * 12;
		emi = (p * r * (float) Math.pow(1 + r, t)) / (float) (Math.pow(1 + r, t) - 1);

		return (emi);
	}

	public float repaymentCal(float principal, float rate, float time) {
		float emi = emi_calculator(principal, rate, time);
		float repaymentCal = emi * time * 12;
		return repaymentCal;
	}
}
