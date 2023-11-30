package com.mongoexample.basicmongo.controllers;

import java.time.Clock;
import java.time.Instant;
import java.util.Random;

import org.bson.Document;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.mongoexample.basicmongo.dao.RockPaperScissorsDAO;

@Controller
public class RockPaperScissorsController {
	private String rpsCollection = "rock_paper_scissors";
	
	private final String rockSelection = "Rock";
	private final String paperSelection = "Paper";
	private final String scissorsSelection = "Scissors";
	private final String[] selectionArray = new String[] {rockSelection, paperSelection, scissorsSelection};
	
	// The following options are not supported
	@Deprecated
	private final String lizardSelection = "Lizard";
	@Deprecated
	private final String spockSelection = "Spock";	
	
	private final String gameResultComputerWins = "Computer Wins";
	private final String gameResultPersonWins = "Person Wins";
	private final String gameResultTie = "Tie";
	private final String gameResultIndeterminate = "Result cannot be determined";
	
	@GetMapping("rock_paper_scissors")
	public String createTemplate(@RequestParam(name="personChoice", required=false, defaultValue = "") String personChoice, Model model) {
		
		try {
			// Determine the computer selection
			Random randomizer = new Random(System.currentTimeMillis());
			String computerChoice = selectionArray[randomizer.nextInt(3)];
			
			// Rock, paper, scissors randomly generated from the computer after the user selects an option would be simple
			String winner = compareChoices(personChoice, computerChoice);
			Instant timestamp = Clock.systemUTC().instant();
			
			System.out.println("timestamp is: " + timestamp);
			
//			Document rpsDocument = new Document("_id", new ObjectId());
//			rpsDocument.append("person_choice", personChoice);
//			rpsDocument.append("computer_choice", computerChoice);
//			rpsDocument.append("winner", winner);
//			rpsDocument.append("creation_status", "generated");
			
			// Only log the entry if there is a choice from the user.
			if ( !personChoice.isEmpty() ) {
				RockPaperScissorsDAO.insertOne(personChoice, computerChoice, winner, timestamp);	
			}
			
			model.addAttribute("personChoice", null == personChoice ? "" : personChoice);
			model.addAttribute("computerChoice", null == computerChoice ? "" : computerChoice);
			model.addAttribute("winner", null == winner ? "" : winner);
			model.addAttribute("timestamp", null == timestamp ? "" : timestamp);
		} catch (Exception e) {
			model.addAttribute("entry", "Exception occurred");
			model.addAttribute("result", e.getMessage());
			model.addAttribute("person", "");
		}
		
		return "rock_paper_scissors";
	}
	
	public String compareChoices(String personSelection, String computerSelection){
		
		System.out.println("personSelection is: " + personSelection);
		System.out.println("computerSelection is: " + computerSelection);
		
		switch (personSelection) {
			case rockSelection:
				if (computerSelection.equals(rockSelection) ) 			{ return gameResultTie; }
				else if (computerSelection.equals(paperSelection) ) 	{ return gameResultComputerWins; }
				else if (computerSelection.equals(scissorsSelection) )	{ return gameResultPersonWins; }
				break;
		
			case paperSelection:
				if (computerSelection.equals(rockSelection) ) 			{ return gameResultPersonWins; }
				else if (computerSelection.equals(paperSelection) ) 	{ return gameResultTie; }
				else if (computerSelection.equals(scissorsSelection) )	{ return gameResultComputerWins; }
				break;
				
			case scissorsSelection:
				if (computerSelection.equals(rockSelection) ) 			{ return gameResultComputerWins; }
				else if (computerSelection.equals(paperSelection) ) 	{ return gameResultPersonWins; }
				else if (computerSelection.equals(scissorsSelection) )	{ return gameResultTie; }
				break;
		}
		
		return gameResultIndeterminate;
	}

}
