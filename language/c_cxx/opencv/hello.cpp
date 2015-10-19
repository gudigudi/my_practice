#include <opencv2/core/core.hpp>
#include <opencv2/highgui/highgui.hpp>

int main()
{
    cv::Mat image;
    image = cv::imread("cat.png");
    cv::namedWindow("cat");
    cv::imshow("cat", image);
    cv::waitKey(5000);

    return 0;
}
