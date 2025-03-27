import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public class MyWindow extends JFrame  {
    private final JButton clickBtnPlay = new JButton("Play");
    private final JButton clickBtnPause = new JButton("Pause");
    private  final JButton clickBtnStop = new JButton("Stop");
    public MyWindow(){
        super("Window");
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setSize(512, 380);
        this.setLocationRelativeTo(null);

        JPanel contentPane = (JPanel) this.getContentPane();
        contentPane.setLayout(new FlowLayout());



        contentPane.add(clickBtnPlay);
        clickBtnPlay.addActionListener(e -> player(e.getActionCommand()));

        contentPane.add(clickBtnPause);
        clickBtnPause.addActionListener(e -> player(e.getActionCommand()));

        contentPane.add(clickBtnStop);
        clickBtnStop.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                player(e.getActionCommand());
            }
        });

//        JLabel label = new JLabel("search bar\n");
//        JTextField searchZone = new JTextField();
//        JTable ourTable = new JTable(3,3);
//
//        contentPane.add(label);
//
//        searchZone.setSize(120,80);
//        contentPane.add(searchZone);
//
//
//        contentPane.add(ourTable);
//        ourTable.setSize(200,120);
    }
    private void player(String eventName){

        String filePath = "Rainbow-Road-Jeremy-Korpas.wav";
        File file = new File(filePath);

        try(AudioInputStream audioStream = AudioSystem.getAudioInputStream(file)){

            Clip clip = AudioSystem.getClip();
            clip.open(audioStream);

            switch (eventName){
                case "Play" -> clip.start();
                case "Pause" -> System.out.println(clip.getControls());
                case "Stop" -> clip.stop();
                default -> System.out.println("Invalid choice");
            }

            System.out.println("ça ira");
//            audioStream.close();
        }
        catch (FileNotFoundException e){
            System.out.println("File not find !!!");
        }
        catch (LineUnavailableException e) {
            System.out.println("Unavailable line");
        }
        catch (UnsupportedAudioFileException e){
            System.out.println("Unsupported File");
        }
        catch (IOException e){
            System.out.println("Something went wrong !");
        }

    }
}
