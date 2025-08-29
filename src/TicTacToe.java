import java.awt.*;
import java.awt.event.*;
import javax.swing.*;


public class TicTacToe {
    int boardWidth = 600;
    int boardHeight = 650;

    JFrame frame = new JFrame("Tic-Tac-Toe");
    JLabel textLabel = new JLabel();
    JPanel textPanel = new JPanel();
    JPanel boardPanel = new JPanel();

    JButton[][] board = new JButton[3][3];
    String playerX = "X";
    String playerO = "O";
    String currentPlayer = playerX;

    boolean gameOver = false;

    int turns = 0;

    TicTacToe() {
        frame.setVisible(true);
        frame.setSize(boardWidth, boardHeight);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        textLabel.setBackground(Color.darkGray);
        textLabel.setForeground(Color.white);
        textLabel.setFont(new Font("Arial", Font.BOLD, 50));
        textLabel.setHorizontalAlignment(JLabel.CENTER);
        textLabel.setText("Tic-Tac-Toe");
        textLabel.setOpaque(true);

        textPanel.setLayout(new BorderLayout());
        textPanel.add(textLabel);
        frame.add(textPanel, BorderLayout.NORTH);

        boardPanel.setLayout(new GridLayout(3, 3));
        boardPanel.setBackground(Color.darkGray);
        frame.add(boardPanel);

        for (int r = 0; r < 3; r++) {
            for (int c = 0; c < 3; c++){
                JButton tile = new JButton();
                board[r][c] = tile;
                boardPanel.add(tile);

                tile.setBackground(Color.darkGray);
                tile.setForeground(Color.white);
                tile.setFont(new Font("Arial", Font.BOLD, 120));
                tile.setFocusable(false);
                
                tile.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e){
                        if (gameOver) return;
                        JButton tile = (JButton) e.getSource();
                        if (tile.getText() == ""){
                            tile.setText(currentPlayer);
                            if (tile.getText() == currentPlayer){
                                currentPlayer = playerO;
                                int row = (int)(Math.random()*3);
                                int collumn = (int)(Math.random()*3);
                                if (board[row][collumn].getText() == "X" || board[row][collumn].getText() == "O"){
                                    row = (int)(Math.random()*3);
                                    collumn = (int)(Math.random()*3);
                                    if (board[row][collumn].getText() == "X" || board[row][collumn].getText() == "O"){
                                        row = (int)(Math.random()*3);
                                        collumn = (int)(Math.random()*3);
                                        if (board[row][collumn].getText() == "X" || board[row][collumn].getText() == "O"){
                                            row = (int)(Math.random()*3);
                                            collumn = (int)(Math.random()*3);
                                            if (board[row][collumn].getText() == "X" || board[row][collumn].getText() == "O"){
                                                row = (int)(Math.random()*3);
                                                collumn = (int)(Math.random()*3);
                                }
                                }
                                }
                                }
                                sleepy();
                                textLabel.setText("O's turn.");
                                board[row][collumn].setText(currentPlayer);
                                textLabel.setText(currentPlayer + "'s turn.");
                                
                                turns++;
                                checkWinner();
                                if(!gameOver) {
                                    currentPlayer = currentPlayer == playerX ? playerO : playerX;
                                    textLabel.setText(currentPlayer + "'s turn.");
                                }
                                    
                            }
                        }

                    }                    
                });
            }
        }
    }
    public static void wait(int ms)
{
    try
    {
        Thread.sleep(ms);
    }
    catch(InterruptedException ex)
    {
        Thread.currentThread().interrupt();
    }
}

    void checkWinner(){ 
        for (int r = 0; r < 3; r++) {
            if (board[r][0].getText() == "") continue;

            if (board[r][0].getText() == board[r][1].getText() && board[r][1].getText() == board[r][2].getText()) {
                for (int i = 0; i < 3; i++) {
                    setWinner(board[r][i]);
                    textLabel.setText(board[r][i].getText() + " is the winner!");
                }
                gameOver = true;
                return;
                }
            
        }
        for (int c = 0; c < 3; c++) {
            if (board[0][c].getText() == "") continue;

            if (board[0][c].getText() == board[1][c].getText() && board[1][c].getText() == board[2][c].getText()) {
                for (int i = 0; i < 3; i++) {
                    setWinner(board[i][c]);
                    textLabel.setText(board[i][c].getText() + " is the winner!");
                }
                gameOver = true;
                return;
                }
            
        }

        if (board[0][0].getText() == board[1][1].getText() && board[1][1].getText() == board[2][2].getText() && board[0][0].getText() != "") {
            for (int i=0; i < 3; i++){
                setWinner(board[i][i]);
                textLabel.setText(board[i][i].getText() + " is the winner!");
            }
            gameOver = true;
            return;
        }

        if (board[0][2].getText() == board[1][1].getText() && board[1][1].getText() == board[2][0].getText() && board[0][2].getText() != "") {
            setWinner(board[0][2]);
            setWinner(board[1][1]);
            setWinner(board[2][0]);
            textLabel.setText(board[2][0].getText() + " is the winner!");
            gameOver = true;
            return;
        }

        if (turns == 9) {
            for (int r = 0; r < 3; r++){
                for (int c = 0; c < 3; c++){
                    setTie(board[r][c]);
                }
            }
            gameOver = true;
        }
        
    }

    void setWinner(JButton tile){
        tile.setForeground(Color.green);
        tile.setBackground(Color.gray);
    }

    void setTie(JButton tile) {
        tile.setForeground(Color.orange);
        tile.setBackground(Color.gray);
        textLabel.setText("Tie!");
    }
    public void sleepy(){
                try {
            // to sleep 10 seconds
            Thread.sleep(100);
        } catch (InterruptedException e) {
            // recommended because catching InterruptedException clears interrupt flag
            Thread.currentThread().interrupt();
            // you probably want to quit if the thread is interrupted
            return;
        }
    }
}
