import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class Main {
    public static void main(String[] args){
        SwingUtilities.invokeLater(()-> {
            JFrame window = new JFrame("Audio-Player");
            window.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
            window.setSize(520, 320);
            window.setLayout(new FlowLayout());

            List<String> playlists = List.of("Rainbow-Road-Jeremy-Korpas.wav","QJFL-_feat.-Axel-Levi.wav","Soutiens-moi-_feat.-DJESS.wav");
//            List playlists = Arrays.asList("Rainbow-Road-Jeremy-Korpas.wav","QJFL-_feat.-Axel-Levi.wav","Soutiens-moi-_feat.-DJESS.wav");

            AudioPlayer player = new AudioPlayer(playlists);

            JButton playButton = new JButton("Play");
            JButton pauseButton = new JButton("Pause");
            JButton stopButton = new JButton("Stop");
            JButton previousButton = new JButton("<<");
            JButton nextButton = new JButton(">>");
            JButton searchBtn = new JButton("Search");

            JPanel firstPanel = new JPanel();
            JPanel secondPanel = new JPanel();

            // Build a TableModel
            DefaultTableModel model = new DefaultTableModel();
            model.addColumn("Title");
            model.addColumn("Author");
            model.addColumn("Size");

            for(String playlist : playlists){
                model.addRow(new Object[]{playlist,"John doe","14MB"});
            }
            JTable table = new JTable(model);
            JTextField searchZone = new JTextField(20);

            firstPanel.add(new JLabel("Search Here"));
            firstPanel.add(searchZone);
            searchZone.setBorder(LineBorder.createGrayLineBorder());
            firstPanel.add(searchBtn);



            secondPanel.add(pauseButton);
            secondPanel.add(playButton);
            secondPanel.add(stopButton);
            secondPanel.add(previousButton);
            secondPanel.add(nextButton);

            JScrollPane scrollPane = new JScrollPane(table);
            
            window.add(firstPanel);
            window.add(secondPanel);
            window.add(scrollPane);

            playButton.setBackground(Color.orange);
            pauseButton.setBackground(Color.white);
            stopButton.setBackground(Color.white);
            previousButton.setBackground(Color.gray);
            nextButton.setBackground(Color.gray);
            searchBtn.setBackground(Color.white);

            playButton.addActionListener(e -> player.play());
            pauseButton.addActionListener(e -> player.pause());
            stopButton.addActionListener(e -> player.stop());
            previousButton.addActionListener(e->player.previous());
            nextButton.addActionListener(e->player.next());



            window.setVisible(true);
        });
    }
}
