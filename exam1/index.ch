〈?xml version='1.0' encoding='UTF-8'?〉
〈!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd"〉

〈html xmlns='http://www.w3.org/1999/xhtml'  
  〈head  
    〈meta content='text/html; charset=utf-8' http-equiv='content-type'〉
    〈title Exam 1〉 
  〉 
  〈body    

    〈h1 San Jose State University | CS 151 - OO Design Section 5 | Fall 2016〉
    〈h2 Exam 1〉

    〈h3 Exam Rules〉
    〈ul
      〈li You may put any files that you like on your laptop, including the textbook, the book code, your and my homework solutions.〉
      〈li You may NOT use the Internet for anything during the exam. 〉
      〈li You may NOT communicate with anyone other than the exam proctor.〉
      〈li All your exam work must be in a folder called 〈 exam1〉 in your repo.〉
      〈li You must run git commit every 10 minutes.〉
      〈li When the exam is over, run git push to push your repo.〉
      〈li The exam is 70 minutes long.〉
    〉
    〈h3 Exam Problems〉

    〈ol
      〈li [10 points] Draw an UML sequence diagram of the first call to 〈 oddSquares.next()〉 in my test case 〈 NumberSequenceTest.filterTest〉 of homework 4. You may trace through your or my solution—tell me which in a note on your diagram. Submit it on paper or in a file 〈 sequence.png〉 in your 〈 exam1〉 directory.〉
      〈li [8 points] Draw an UML class diagram of the classes and interfaces in the solution of homework 5. Indicate multiplicities as appropriate. You may use your or my solution—tell me which in a note on your diagram. Submit it on paper or in a file 〈 hw5class.png〉 in your 〈 exam1〉 directory.〉
      〈li [15 points] In homework 2b, the message text in the 〈 Message〉 class was a 〈 String〉 object. Change it to an interface 〈 Text〉 with a single method to get the content. Supply two classes 〈 PlainText〉 and 〈 OneTimeText〉 that implement this interface. A 〈 OneTimeText〉 message can be read once, and afterwards it yields an empty string. Make the appropriate changes so that a 〈 Message〉 has a 〈 Text〉 object, and so that its 〈 getText〉 method returns the contents. Change the user interface so that, after the user has selected the 〈 [S]end message〉 menu option, a menu
        〈pre
[P]lain text  [O]ne time text〉
        is displayed, and construct a message with one or the other kind of text.〉
      〈li [10 points] Consider 〈a href=Length.java this class〉 that represents units in feet and inches. Change the internal representation to use only inches, without affecting clients of the class.〉
      〈li [8 points] As you can see from 〈a href=FileChooserDemo.java this program〉, you can restrict the files that are displayed in a 〈 JFileChooser〉 by passing a class that implements 〈 FileFilter〉. Make a table similar to those in Section 5.4.3 of your textbook to show why this is an example of the STRATEGY pattern. Submit as a plain text file 〈 strategy.txt〉.〉
    〉
  〉
〉
