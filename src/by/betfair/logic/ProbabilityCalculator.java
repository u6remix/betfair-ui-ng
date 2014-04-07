package by.betfair.logic;

import org.apache.log4j.Logger;
import by.betfair.ui.model.Market;
import by.betfair.ui.model.Runner;

public class ProbabilityCalculator {

	private static final Logger logger = Logger.getLogger(ProbabilityCalculator.class);

	/**
	 * Calculates probabilities by amount and fair probabilities by odds for each suitable runner of market
	 * @param market
	 */
	public static void calculateProbability(Market market) {
		// calculate and set probabilities by amounts
		for (Runner runner : market.getRunners()) {
			if (runner.getTotalMatched() != 0) {
				double probabilityByAmount = runner.getTotalMatched() / market.getTotalMatched()*100;
				runner.setProbabilityByAmounts(probabilityByAmount);
			} else {
				logger.debug("total Matched is 0 for runner: " + runner.getRunnerName());
			}
		}
		// set fair probabilities by odds
		if (market.getTotalProbabilityByOdds()!= 0) {
			for (Runner runner : market.getRunners()) {
				double fairProbability = runner.getProbabilityByOdds() * 100 / market.getTotalProbabilityByOdds();
				runner.setFairProbabilityByOdds(fairProbability);
			}
		} else {
			logger.debug("can't calculate fair probabilities because total probability by odds = 0");
		}

	}
}
