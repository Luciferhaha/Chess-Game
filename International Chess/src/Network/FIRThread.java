package Network;

import java.io.IOException;
import java.util.StringTokenizer;

public class FIRThread extends Thread
{
	ChessBoard2 currPad; // 当前线程的棋盘
	PiecesMove2 move;
	public FIRThread(ChessBoard2 currPad)
	{
		this.currPad = currPad;
		
	}
	public FIRThread(PiecesMove2 move2) {
		// TODO Auto-generated constructor stub
		this.move=move2;
	}
	// 处理取得的信息
	public void dealWithMsg(String msgReceived)
	{
		if (msgReceived.startsWith("/chess "))
		{ // 收到的信息为下棋
			System.out.println(msgReceived);
			StringTokenizer userMsgToken = new StringTokenizer(msgReceived, " ");
			// 表示棋子信息的数组、0索引为：x坐标；1索引位：y坐标；2索引位：棋子类型
			String chessInfo[]=new String[5];
			String chessInfo2[]=new String[6];
			int i = 0,j=0,k=0; // 标志位
			String chessInfoToken;
			System.out.println(userMsgToken.countTokens());
			if (userMsgToken.countTokens()==6) {
				while (userMsgToken.hasMoreTokens())
				{
					chessInfoToken = (String) userMsgToken.nextToken(" ");
					if (i >= 1 && i <= 5)
					{
						chessInfo[i-1] = chessInfoToken;
					}
					i++;
				}
				currPad.arrivemessage(Integer.parseInt(chessInfo[0]), Integer.parseInt(chessInfo[1]), 
						Integer.parseInt(chessInfo[2]),Integer.parseInt(chessInfo[3]),chessInfo[4]);
				
			}else if (userMsgToken.countTokens()==7) {
				System.out.println("l got it2");
				while (userMsgToken.hasMoreTokens())
				{
					chessInfoToken = (String) userMsgToken.nextToken(" ");
					if (j >= 1 && j <= 6)
					{
						chessInfo2[j-1] = chessInfoToken;
					}
					j++;
				}
				currPad.arriveEatMessage(Integer.parseInt(chessInfo2[0]), Integer.parseInt(chessInfo2[1]), 
						Integer.parseInt(chessInfo2[2]),Integer.parseInt(chessInfo2[3]),chessInfo2[4],chessInfo2[5]);
			}else if (userMsgToken.countTokens()==2) {
				System.out.println("l got it 3");
				String[] info3=new String[2];
				while (userMsgToken.hasMoreTokens())
				{
					chessInfoToken = (String) userMsgToken.nextToken(" ");
					if (k>= 1 && k<= 1)
					{
						info3[k-1] = chessInfoToken;
					}
					k++;
				}
				currPad.arriveCastingmessage(info3[0],info3[2]);
			}else if (userMsgToken.countTokens()==1) {
				System.out.println("l got it 4");
				currPad.arrivePromotion(userMsgToken);
			}
		}
		else if (msgReceived.startsWith("/yourname "))
		{ // 收到的信息为改名
			currPad.chessSelfName = msgReceived.substring(10);
		}
		else if (msgReceived.equals("/error"))
		{ // 收到的为错误信息
			System.out.println("用户不存在，请重新加入!");
		}
	}
	
	// 发送信息
	public void sendMessage(String sndMessage)
	{
		try
		{
			currPad.outputData.writeUTF(sndMessage);
			System.out.println(sndMessage);
			currPad.chessPlayClick=1;
		}
		catch (Exception ea)
		{
			ea.printStackTrace();;
		}
	}

	public void run()
	{
		String msgReceived = "";
		try
		{
			while (true)
			{ // 等待信息输入
				msgReceived = currPad.inputData.readUTF();
				System.out.println(msgReceived);
				dealWithMsg(msgReceived);
				System.out.println("run");
			}
		}
		catch (IOException es){}
	}
}
