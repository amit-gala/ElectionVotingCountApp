# ElectionVotingCountApp
Steps to run:
1) This is a maven project, so load and build into the IDE.
2) In properties file, change the final output log file (result at 5 sec interval)
3) Run the application
4) Use the endpoint to start the N producers: say N=10http://localhost:9090/producer/10 
5) Use the endpoint to start N consumers: say N = 2http://localhost:9090/consumer/2
6) For input, a fixed file is being used by every producer from location: resources/testData/votes.txt
7) For 5 sec interval a scheduled method is being triggered to fetch the result and log into the o/p file set in properties file.
8) In this implementation project, i have used a HashMap implementation instead of a database for simplicity.
