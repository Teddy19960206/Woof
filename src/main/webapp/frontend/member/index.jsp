
<script type="text/javascript">
    function refresh() {
   
   
     src="index.jsp?id="+Math.random();
    }
</script>
<%@ page contentType="charset=UTF-8" language="java"
        import ="java.awt.*"
         import ="java.awt.image.BufferedImage"
          import="java.util.*"
         import="javax.imageio.ImageIO"
         pageEncoding="gb2312"%>
<%
response.setHeader("Cache-Control","no-cache");
//�b�O���餤�إ߼v�H
  int width=60,height=20;
  BufferedImage image=new BufferedImage(width,height,BufferedImage.TYPE_INT_RGB);
  //����e��
  Graphics g=image.getGraphics();
  //�]�w�I����
  g.setColor(new Color(200,200,200));
  g.fillRect(0,0,width,height);
  //���H�����ͪ����ҽX(4��Ʀr)
  Random rnd=new Random();
  int randNum=rnd.nextInt(8999)+1000;
  String randStr=String.valueOf(randNum);
  //�N���ҽX�s�Jsession
  session.setAttribute("randStr",randStr);
  //�N���ҽX��ܨ�v�H��
  g.setColor(Color.black);
  g.setFont(new Font("", Font.PLAIN,20));
  g.drawString(randStr,10,17);
  //�H������100�Ӥz�Z�I�A�ϼv�H�������ҽX�����Q��L�{��������
    for (int i = 0; i < 100; i++) {
   
   
        int x=rnd.nextInt(width);
        int y=rnd.nextInt(height);
        g.drawOval(x,y,1,1);
    }
    //��X�v�H�쭶��
    ImageIO.write(image,"JPEG",response.getOutputStream());
    out.clear();
    out=pageContext.pushBody();

%>

