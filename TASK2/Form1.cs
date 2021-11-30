using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace TASK2
{
    public partial class Form1 : Form
    {
        public Form1()
        {
            InitializeComponent();
        }

        private void pictureBox1_Click(object sender, EventArgs e)
        {
            Graphics g;    //  графический объект — некий холст
            Bitmap buf;    //  буфер для Bitmap-изображения

            buf = new Bitmap(pictureBox1.Width, pictureBox1.Height);  // с размерами
            for (int i = 0; i < 200; i++)
            {
                for (int j = 0; j < 200; j++)
                {
                    buf.SetPixel(i, j, Color.Black);
                }
            }
            
            g = Graphics.FromImage(buf);   // инициализация g
        }
    }
}
