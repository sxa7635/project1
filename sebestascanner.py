# token codes
INT_LIT=10
LETTER=0
DIGIT=1
IDENT=11
ASSIGN_OP=20
ADD_OP=21
SUB_OP=22
MULT_OP=23
DIV_OP=24
LEFT_PAREN=25
RIGHT_PAREN=26
UNKNOWN=99
EOF=-1
lexlen=0
lexeme=""
# getchar function for getting the characters from the input file
def getChar():
        global nextChar   # declaring global variables
        global charClass
        global lexeme
        nextChar=fp.read(1)

       
        if nextChar!="NULL":
            if nextChar.isalpha():
               charClass=LETTER
            elif nextChar.isdigit():
               charClass=DIGIT
            else:
               charClass=UNKNOWN
        else:
            charClass=EOF
            lexeme= "EOF"
# addchar function for adding the characters to lexeme
def addChar():
        global lexeme
        global nextChar
        global lexlen

        if lexlen <= 98:
            lexeme += nextChar


        else:
            print "Error:lexeme is too long"

# getNonBlank function to remove spaces from being read
def getNonBlank():
        global nextChar
        while nextChar.isspace():
            getChar()


# lookup function for the operators
def lookup(ch):
        global nextToken
        global lexeme
        if(ch=='('):
            addChar()
            nextToken=LEFT_PAREN

        elif(ch==')'):
            addChar()
            nextToken=RIGHT_PAREN

        elif(ch=='+'):
            addChar()
            nextToken=ADD_OP

        elif(ch=='-'):
            addChar()
            nextToken=SUB_OP

        elif(ch=='*'):
            addChar()
            nextToken=MULT_OP

        elif(ch=='/'):
            addChar()
            nextToken=DIV_OP

        else:
            addChar()
            nextToken=EOF
            lexeme = "EOF"
        return nextToken


# lex function 
def  lex():
        global lexeme
        global charClass
        global nextToken
        lexeme = ""
        getNonBlank()
        if charClass==LETTER:
            addChar()
            getChar()
            while charClass==LETTER:
                addChar()
                getChar()

            nextToken = IDENT

        elif charClass==DIGIT:
            addChar()
            getChar()
            while charClass==DIGIT:
                addChar()
                getChar()

            nextToken = INT_LIT

        elif charClass==UNKNOWN:
            nextToken = lookup(nextChar)
            getChar()

        else:
            nextToken = EOF
            lexeme='EOF'

        print "  next token is  " + str(nextToken) + "  next lexeme is  " + "  " +lexeme
        return nextToken
# main function
if __name__ == '__main__':
    global fp
    parse = 0

    fp = open('input.txt', 'r')
    parse=1
    if parse:
        getChar()
        lex()
        while nextToken != EOF:
          lex()
        fp.close()

            
            
    
  
        
       
    
    
        
    
    

    
