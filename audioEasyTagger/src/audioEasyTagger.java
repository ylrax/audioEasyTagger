//

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JScrollPane;
import java.awt.GridBagLayout;
import javax.swing.JButton;
import java.awt.GridBagConstraints;
import javax.swing.JCheckBox;
import java.awt.Insets;
import javax.swing.SwingConstants;
import javax.swing.JTextField;


import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;

import org.jaudiotagger.audio.AudioFile;
import org.jaudiotagger.audio.AudioFileIO;
import org.jaudiotagger.audio.exceptions.CannotReadException;
import org.jaudiotagger.audio.exceptions.InvalidAudioFrameException;
import org.jaudiotagger.audio.exceptions.ReadOnlyFileException;
import org.jaudiotagger.tag.FieldKey;
import org.jaudiotagger.tag.Tag;
import org.jaudiotagger.tag.TagException;


public class audioEasyTagger extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private final JTextField txtNewArtist;
	private JTextField txtNewAlbum;
	private JList listOfFilesToTag = new JList<Object>();
	private JList listArtists = new JList<Object>();
	private JList listAlbums = new JList<Object>();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					audioEasyTagger frame = new audioEasyTagger();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */


	public audioEasyTagger() {

		/////////////////////////////////////////////////////////////////////////////////////////////////
		// Here we define all the elements in the frame:
		/////////////////////////////////////////////////////////////////////////////////////////////////


		setTitle("mp3 tagger");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 767, 434);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));

		final JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.WEST);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[]{0, 172, 0};
		gbl_panel.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_panel.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		gbl_panel.rowWeights = new double[]{0.0, 0.0, 1.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		panel.setLayout(gbl_panel);

		JButton btnNewButton = new JButton("Choose folder");
		btnNewButton.setVerticalAlignment(SwingConstants.BOTTOM);
		GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
		gbc_btnNewButton.gridwidth = 2;
		gbc_btnNewButton.insets = new Insets(0, 0, 5, 0);
		gbc_btnNewButton.gridx = 0;
		gbc_btnNewButton.gridy = 0;
		panel.add(btnNewButton, gbc_btnNewButton);

		JLabel lblNewLabel = new JLabel("Artists found:");
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.gridwidth = 2;
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 0);
		gbc_lblNewLabel.gridx = 0;
		gbc_lblNewLabel.gridy = 1;
		panel.add(lblNewLabel, gbc_lblNewLabel);

		final JScrollPane scrollPane_Artists = new JScrollPane();
		GridBagConstraints gbc_scrollPane_Artists = new GridBagConstraints();
		gbc_scrollPane_Artists.gridwidth = 2;
		gbc_scrollPane_Artists.insets = new Insets(0, 0, 5, 0);
		gbc_scrollPane_Artists.fill = GridBagConstraints.BOTH;
		gbc_scrollPane_Artists.gridx = 0;
		gbc_scrollPane_Artists.gridy = 2;
		panel.add(scrollPane_Artists, gbc_scrollPane_Artists);
		scrollPane_Artists.setViewportView(listArtists);


		listArtists.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));

		JLabel lblCommonArtists = new JLabel("Albums found:");
		GridBagConstraints gbc_lblCommonArtists = new GridBagConstraints();
		gbc_lblCommonArtists.gridwidth = 2;
		gbc_lblCommonArtists.insets = new Insets(0, 0, 5, 0);
		gbc_lblCommonArtists.gridx = 0;
		gbc_lblCommonArtists.gridy = 3;
		panel.add(lblCommonArtists, gbc_lblCommonArtists);

		final JScrollPane scrollPane_Albums = new JScrollPane();
		GridBagConstraints gbc_scrollPane_Albums = new GridBagConstraints();
		gbc_scrollPane_Albums.gridwidth = 2;
		gbc_scrollPane_Albums.insets = new Insets(0, 0, 5, 0);
		gbc_scrollPane_Albums.fill = GridBagConstraints.BOTH;
		gbc_scrollPane_Albums.gridx = 0;
		gbc_scrollPane_Albums.gridy = 4;
		panel.add(scrollPane_Albums, gbc_scrollPane_Albums);

		scrollPane_Albums.setViewportView(listAlbums);
		listAlbums.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));

		final JCheckBox chckbxChangeArtist = new JCheckBox("Change artist");
		GridBagConstraints gbc_chckbxChangeArtist = new GridBagConstraints();
		gbc_chckbxChangeArtist.insets = new Insets(0, 0, 5, 5);
		gbc_chckbxChangeArtist.gridx = 0;
		gbc_chckbxChangeArtist.gridy = 5;
		panel.add(chckbxChangeArtist, gbc_chckbxChangeArtist);

		txtNewArtist = new JTextField();
		txtNewArtist.setText("New Artist");
		GridBagConstraints gbc_txtNewArtist = new GridBagConstraints();
		gbc_txtNewArtist.insets = new Insets(0, 0, 5, 0);
		gbc_txtNewArtist.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtNewArtist.gridx = 1;
		gbc_txtNewArtist.gridy = 5;
		panel.add(txtNewArtist, gbc_txtNewArtist);
		txtNewArtist.setColumns(10);

		final JCheckBox chckbxChangeAlbum = new JCheckBox("Change album");
		GridBagConstraints gbc_chckbxChangeAlbum = new GridBagConstraints();
		gbc_chckbxChangeAlbum.insets = new Insets(0, 0, 5, 5);
		gbc_chckbxChangeAlbum.gridx = 0;
		gbc_chckbxChangeAlbum.gridy = 6;
		panel.add(chckbxChangeAlbum, gbc_chckbxChangeAlbum);

		txtNewAlbum = new JTextField();
		txtNewAlbum.setText("New Album");
		GridBagConstraints gbc_txtNewAlbum = new GridBagConstraints();
		gbc_txtNewAlbum.insets = new Insets(0, 0, 5, 0);
		gbc_txtNewAlbum.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtNewAlbum.gridx = 1;
		gbc_txtNewAlbum.gridy = 6;
		panel.add(txtNewAlbum, gbc_txtNewAlbum);
		txtNewAlbum.setColumns(10);

		final JButton btnChangeLabels = new JButton("Change labels");
		btnChangeLabels.setBackground(Color.RED);
		GridBagConstraints gbc_btnChangeLabels = new GridBagConstraints();
		gbc_btnChangeLabels.gridwidth = 2;
		gbc_btnChangeLabels.gridx = 0;
		gbc_btnChangeLabels.gridy = 8;
		panel.add(btnChangeLabels, gbc_btnChangeLabels);

		final JScrollPane scrollPane = new JScrollPane();
		contentPane.add(scrollPane, BorderLayout.CENTER);

		JLabel lblFolder = new JLabel("Files:");
		scrollPane.setColumnHeaderView(lblFolder);
		listOfFilesToTag.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		scrollPane.setViewportView(listOfFilesToTag);

		/////////////////////////////////////////////////////////////////////////////////////////////////
		// here we track the activity.
		/////////////////////////////////////////////////////////////////////////////////////////////////


		// this object will contain the list of files that we want to change.

		// Here we configure the button that selects the folder that we want to tag.		
		btnNewButton.addActionListener(new ActionListener() {
			public  void actionPerformed(ActionEvent e) {

				//first, if the user press the button a menu appears in which he chooses the folder
				JFileChooser f = new JFileChooser();
				f.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY); 
				f.showSaveDialog(null);


				// we display the chosen file in the panel

				try {

					final TagAnalyser analyser = new TagAnalyser(f.getSelectedFile());

					listOfFilesToTag= new JList(analyser.getListOfFilesString().toArray());
					scrollPane.setViewportView(listOfFilesToTag);

					// here we display the Artists and the Albums in the JList

					ArrayList<String> listOfAlbumsWithFrequencies = new ArrayList<String>();
					int i=0;
					for (String album: analyser.getListOfAlbumsSortedByFrequency()){
						listOfAlbumsWithFrequencies.add(album + " (" + analyser.getListOfFrequenciesAlbums().get(i) + ")" );
						i++;
					}
					listAlbums = new JList(listOfAlbumsWithFrequencies.toArray());
					scrollPane_Albums.setViewportView(listAlbums);

					ArrayList<String> listOfArtistsWithFrequencies = new ArrayList<String>();
					int j=0;
					for (String artist: analyser.getListOfArtistsSortedByFrequency()){
						listOfArtistsWithFrequencies.add(artist + " (" + analyser.getListOfFrequenciesArtists().get(j) + ")" );
						j++;
					}
					listArtists = new JList(listOfArtistsWithFrequencies.toArray());
					scrollPane_Artists.setViewportView(listArtists);







					// here we get the chosen artist from the list of frequent artists tags
					MouseListener mouseListenerArtists = new MouseAdapter() {
						public void mouseClicked(MouseEvent e) {
							if (e.getClickCount() == 2) {
								int selectedArtist= listArtists.getSelectedIndex();
								txtNewArtist.setText(analyser.getListOfArtistsSortedByFrequency().get(selectedArtist));

							}
						}
					};


					listArtists.addMouseListener(mouseListenerArtists);

					MouseListener mouseListenerAlbum = new MouseAdapter() {
						public void mouseClicked(MouseEvent e) {
							if (e.getClickCount() == 2) {
								int selectedAlbum= listAlbums.getSelectedIndex();
								txtNewAlbum.setText(analyser.getListOfAlbumsSortedByFrequency().get(selectedAlbum));
							}
						}
					};


					listAlbums.addMouseListener(mouseListenerAlbum);

					// here we change the labels using the class TagChanger and the library mp3Magic.


					// this part is active when the user presses the btnChangeLabels
					@SuppressWarnings("unused")
					ListenerChangeLabelsButton listener = new ListenerChangeLabelsButton(btnChangeLabels, chckbxChangeAlbum,txtNewAlbum, analyser.getListOfFiles(), chckbxChangeArtist, txtNewArtist);
					
					
					
				} catch (IOException | CannotReadException | TagException | ReadOnlyFileException | InvalidAudioFrameException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
			}
		});
	}

}
